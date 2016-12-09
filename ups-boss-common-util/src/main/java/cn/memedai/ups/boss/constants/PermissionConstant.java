package cn.memedai.ups.boss.constants;

import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

/**
 * 会话键常量类.
 * @author tongxiong.cheng
 * @date 2016-11-29 下午2:14:27
 * @version 1.0
 */
@Slf4j
public class PermissionConstant {
	

	/**
	 * 登录操作员的session键名.
	 */
	public static final String OPERATOR_SESSION_KEY = "pmsOperator";

	/**
	 * 登录操作员拥有的权限集合的session键名.
	 */
	public static final String ACTIONS_SESSION_KEY = "actions";
	
	/**
	 * 超级管理员角色ID
	 */
	public static final long ADMIN_ROLE_ID = 1L;

//
//	/**
//	 * 运营操作员会话键
//	 */
//	public static String WEB_OPERATOR_KEY = "WebOperators";
	
	/**
	 * 操作员在线用户数限制(默认100).
	 */
	public static int WEB_ONLINE_LIMIT = 100;
	
	/**
	 * 操作员密码连续输错次数限制(默认5).
	 */
	public static int WEB_PWD_INPUT_ERROR_LIMIT = 5;
	
	/**
	 * 只加载一次.
	 */
	static {
		try {
			log.info("=== load permission.properties and init");
			InputStream proFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("permission.properties");
			Properties props = new Properties();
			props.load(proFile);
			init(props);
		} catch (Exception e) {
			log.error("=== load and init permission.properties exception:" + e);
		}
	}
	
	/**
	 * 根据配置文件初始化静态变量值.
	 * 
	 * @param props
	 */
	private static void init(Properties props) {
//		String web_operator_key_prefix = props.getProperty("web.operator.key.prefix");
//		if (StringUtils.isNotBlank(web_operator_key_prefix)) {
//			WEB_OPERATOR_KEY = web_operator_key_prefix + WEB_OPERATOR_KEY;
//			LOG.info("===>WEB_OPERATOR_KEY:" + WEB_OPERATOR_KEY);
//		}
		
		String web_online_limit = props.getProperty("web.online.limit");
		if (StringUtils.isNotBlank(web_online_limit)) {
			WEB_ONLINE_LIMIT = Integer.valueOf(web_online_limit);
			log.info("===>WEB_ONLINE_LIMIT:{}",WEB_ONLINE_LIMIT);
		}
		
		String web_pwd_input_error_limit = props.getProperty("web.pwd.input.error.limit");
		if (StringUtils.isNotBlank(web_pwd_input_error_limit)) {
			WEB_PWD_INPUT_ERROR_LIMIT = Integer.valueOf(web_pwd_input_error_limit);
			log.info("===>WEB_PWD_INPUT_ERROR_LIMIT:{}",WEB_PWD_INPUT_ERROR_LIMIT);
		}
	}

}
