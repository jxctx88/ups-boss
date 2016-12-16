package cn.memedai.ups.boss.webapp.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.memedai.ups.boss.constants.PermissionConstant;
import cn.memedai.ups.boss.dal.model.PmsOperatorDO;

/**
 * 
 * @descript: 操作员登录之后的操作权限拦截器.
 * @author: chengtx
 * @创建时间: 2016-11-26,下午11:06:57
 *
 */
public class OperatorLoginInterceptor implements HandlerInterceptor {

	private static final Log log = LogFactory.getLog(OperatorLoginInterceptor.class);
	
	/**
	 * 设置登录跳转url
	 * 
	 * @param context
	 */
	@SuppressWarnings("unchecked")
	private void setToGoUrl(HttpServletRequest request) {
		String action = request.getServletPath();
		String para = "?";
		Map<String,String[]> paramMap = request.getParameterMap();
		for (Object o : paramMap.keySet()) {
			try {
				String[] value = (String[]) paramMap.get(o);
				if (value.length > 0) {
					para += o.toString() + "=" + value[0] + "&";
				}
			} catch (Exception e) {
				try {
					para += o.toString() + "=" + paramMap.get(o) + "&";
				} catch (Exception e1) {

				}
			}
		}
		request.getSession().setAttribute("returnUrlPmsOperator", action + para);
		log.info("跳转URL：" + request.getSession().getAttribute("returnUrlPmsOperator"));

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj)
			throws Exception {
		
		//登录操作员的session键名.
		PmsOperatorDO operator = (PmsOperatorDO)request.getSession().getAttribute(PermissionConstant.OPERATOR_SESSION_KEY);
		
		// 判断用户是否已登录
		if (operator == null) {
			// 用户未登录
			setToGoUrl(request);
			log.info("action[" + request.getServletPath() + "], Operator no login!");
			// 跳转到未登录页面
			request.getRequestDispatcher("/operatorLogin").forward(request, response);
		}
		
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			ModelAndView modelandview) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			Exception exception) throws Exception {
		
	}

}
