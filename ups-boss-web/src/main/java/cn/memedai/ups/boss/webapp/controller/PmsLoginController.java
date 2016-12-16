package cn.memedai.ups.boss.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.memedai.ups.boss.constants.GlobalConstant;
import cn.memedai.ups.boss.constants.PermissionConstant;
import cn.memedai.ups.boss.dal.model.PmsActionDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.enums.OperatorStatusEnum;
import cn.memedai.ups.boss.service.permission.PmsActionService;
import cn.memedai.ups.boss.service.permission.PmsMenuService;
import cn.memedai.ups.boss.service.permission.PmsOperatorService;
import cn.memedai.ups.boss.service.permission.PmsRoleService;
import cn.memedai.ups.boss.service.permission.exception.PermissionException;
import cn.memedai.ups.boss.webapp.base.PermissionBase;

import com.google.code.kaptcha.Constants;
/**
 * 登录控制类
 * @author tongxiong.cheng
 * @date 2016-11-29 下午3:46:35
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class PmsLoginController extends PermissionBase {
	/**
	 * 跳转到登录页面请求URL
	 */
	private static final String LOGIN_URL = "login";
	
	//权限服务
	@Autowired
	private PmsActionService pmsActionService;
	
	//角色服务
	@Autowired
	private PmsRoleService pmsRoleService;
	
	//操作员服务
	@Autowired
	private PmsOperatorService pmsOperatorService;
	
	//菜单服务
	@Autowired
	private PmsMenuService pmsMenuService;

	/**
	 * 进入登录页面.
	 * @return
	 */
	@RequestMapping("/loginPage")
	public String loginPage() {
		return "login";
	}

	/**
	 * 登录验证Action
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/operatorLogin")
	public String operatorLogin(HttpServletRequest request,HttpSession session,Map<String,Object> map) {
		try {
			// TODO 测试使用，暂时注释验证码校验
			
			// 如果会话还存在的情况下则不检验验证码，否则会遇到刷新时跳到登录页面的问题
			// 明文用户名
			String loginName = (String) request.getParameter("loginName");

			if (StringUtils.isBlank(loginName)) {
				request.setAttribute("loginNameMsg", "用户名不能为空");
				return LOGIN_URL;
			}
			
			request.setAttribute("loginName", loginName);
			
			//获取登录员信息
			Object operatorSession = session.getAttribute(PermissionConstant.OPERATOR_SESSION_KEY);
			if (operatorSession == null) {
				// 校验验证码是否正确
				String code = (String) request.getParameter("code");
				String kaptchaCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
				if (StringUtils.isBlank(code)) {
					request.setAttribute("codeMsg", "请输入验证码");
					return LOGIN_URL;
				} else if (!code.equalsIgnoreCase(kaptchaCode)) {
					// 忽略大小写的对比
					request.setAttribute("codeMsg", "验证码不正确");
					return LOGIN_URL;
				}
			}

			// 定义一个result来记录Action的定向
			// String result = "input";
			PmsOperatorDO operator = pmsOperatorService.findByLoginName(loginName);
			if (operator == null) {
				log.warn("==> 登录名[" + loginName + "]不存在");
				request.setAttribute("loginNameMsg", "用户名或密码不正确");
				return LOGIN_URL;
			}

			if (Integer.parseInt(operator.getStatus()) == OperatorStatusEnum.INACTIVE.getValue()) {
				log.warn("== 帐号【" + loginName + "】已被冻结");
				request.setAttribute("loginNameMsg", "该帐号已被冻结");
				return LOGIN_URL;
			}

			String pwd = request.getParameter("loginPwd");
			if (StringUtils.isBlank(pwd)) {
				request.setAttribute("loginPwdMsg", "密码不能为空");
				return LOGIN_URL;
			}

			// 加密明文密码，验证密码
			if (operator.getLoginpwd().equals(DigestUtils.sha1Hex(pwd))) {
				// 密码正确
				
				// 当前在线人数
				if (GlobalConstant.CURRENT_LOGIN_COUNT > PermissionConstant.WEB_ONLINE_LIMIT) {
					log.info("==>系统繁忙，已超最大在线用户数限制【" + PermissionConstant.WEB_ONLINE_LIMIT + "】");
					request.setAttribute("errorMsg", "系统繁忙，已超最大在线用户数限制");
					return LOGIN_URL;
				}

				// 用户信息，包括登录信息和权限
				request.getSession().setAttribute(PermissionConstant.OPERATOR_SESSION_KEY, operator);
				request.getSession().setAttribute(PermissionConstant.ACTIONS_SESSION_KEY, getActions(operator));
				
				request.setAttribute("loginName", loginName);
				// 设置操作员的真实姓名
				request.setAttribute("realName", operator.getRealname());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
				request.setAttribute("lastLoginTime", (operator.getLastlogintime()==null) ? "" : sdf.format(operator.getLastlogintime()));
				
				try {
					// 获取用户的菜单权限
					request.setAttribute("tree", buildOperatorPermissionMenu(operator));

					// 更新登录数据
					operator.setLastlogintime(new Date());
					operator.setPwderrorcount((short)0); // 错误次数设为0
					pmsOperatorService.update(operator);

				} catch (Exception e) {
					log.error("==>登录异常:", e);
					super.logLoginError("登录出错", operator);
					request.setAttribute("errorMsg", "登录出错");
					return LOGIN_URL;
				}
				// 记录系统操作日志
				super.logLogin("登录系统");
				// 判断用户是否重置了密码，如果重置，弹出强制修改密码页面； TODO
				request.getSession().setAttribute("isChangePwd", operator.getIschangedpwd());

				return "index";

			} else {
				// 密码错误
				log.warn("==>密码错误");
				// 错误次数加1
				Short pwdErrorCount = operator.getPwderrorcount();
				if (pwdErrorCount == null){
					pwdErrorCount = 0;
				}
				operator.setPwderrorcount((short)(pwdErrorCount + 1));
				operator.setPwderrortime(new Date()); // 设为当前时间
				String msg = "";
				if (operator.getPwderrorcount().intValue() >= PermissionConstant.WEB_PWD_INPUT_ERROR_LIMIT) {
					// 超5次就冻结帐号
					operator.setStatus(OperatorStatusEnum.INACTIVE.getValue()+"");
					msg += "<br/>密码已连续输错【" + PermissionConstant.WEB_PWD_INPUT_ERROR_LIMIT + "】次，帐号已被冻结";
					super.logLoginError("登录出错,密码已连续输错【" + PermissionConstant.WEB_PWD_INPUT_ERROR_LIMIT + "】次，帐号已被冻结", operator);
				} else {
					msg += "<br/>密码错误，再输错【" + (PermissionConstant.WEB_PWD_INPUT_ERROR_LIMIT - operator.getPwderrorcount().intValue()) + "】次将冻结帐号";
					super.logLoginError("登录出错,密码输入错误.剩余[" + (PermissionConstant.WEB_PWD_INPUT_ERROR_LIMIT - operator.getPwderrorcount()) + "]次机会。", operator);
				}

				pmsOperatorService.update(operator);
				request.setAttribute("loginPwdMsg", msg);
				return LOGIN_URL;
			}

		} catch (RuntimeException e) {
			log.error("login exception:", e);
			session.setAttribute("errorMsg", "登录出错");
			return LOGIN_URL;
		} catch (Exception e) {
			log.error("login exception:", e);
			session.setAttribute("errorMsg", "登录出错");
			return LOGIN_URL;
		}
	}

	/**
	 * 跳转到退出确认页面.
	 * 
	 * @return LogOutConfirm.
	 */
	@RequestMapping("/logoutConfirm")
	public String logoutConfirm() {
		log.info("==> logoutConfirm");
		return "logoutConfirm";
	}

	/**
	 * 退出登录 .
	 * @return logout.
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		log.info("==>logout,clear session");
		return "login";
	}

	/**
	 * 跳转到登录超时确认页面.
	 * 
	 * @return timeoutConfirm.
	 */
	@RequestMapping("/timeoutConfirm")
	public String timeoutConfirm() {
		log.info("==>timeoutConfirm");
		return "timeoutConfirm";
	}

	/**
	 * 得到以权限（Action）为key，以PmsAction为Value的指定用户的权限Map
	 * 
	 * @param pmsOperator
	 * @return
	 */
	private List<String> getActions(PmsOperatorDO pmsOperator) {
		// 根据用户ID得到该用户的所有角色拼成的字符串
		String roleIds = pmsRoleService.getRoleIdsByOperatorId(pmsOperator.getId());
		// 根据角色ID字符串得到该用户的所有权限拼成的字符串
		String actionIds = "";
		if (StringUtils.isNotBlank(roleIds)) {
			actionIds = pmsActionService.getActionIdsByRoleIds(roleIds);
		}
		// 根据权限ID字符串得到权限列表
		List<PmsActionDO> pmsActionList = new ArrayList<PmsActionDO>();
		if (!"".equals(actionIds)) {
			pmsActionList = pmsActionService.findByIds(actionIds);
		}
		// 将权限放入HashMap中，其中key为权限（action），值为权限对象
		List<String> actionList = new ArrayList<String>();
		for (PmsActionDO pmsAction : pmsActionList) {
			actionList.add(pmsAction.getAction());
		}
		return actionList;
	}

	/**
	 * 获取用户的菜单权限
	 * 
	 * @param pmsOperator
	 * @return
	 * @throws Exception
	 */
	private String buildOperatorPermissionMenu(PmsOperatorDO pmsOperator) throws PermissionException {
		// 根据用户ID得到该用户的所有角色拼成的字符串
		/*String roleIds = pmsRoleService.getRoleIdsByOperatorId(pmsOperator.getId());
		if (StringUtils.isBlank(roleIds)) {
			log.error("==>用户[" + pmsOperator.getLoginname() + "]没有配置对应的权限角色");
			throw new RuntimeException("该帐号已被取消所有系统权限");
		}
		// 根据操作员拥有的角色ID,构建管理后台的树形权限功能菜单
		return pmsMenuService.buildPermissionTree(roleIds);*/
		return pmsMenuService.buildOperatorPermissionMenu(pmsOperator.getId());
	}

}
