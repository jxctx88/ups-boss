package cn.memedai.ups.boss.webapp.interceptor;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import cn.memedai.ups.boss.constants.PermissionConstant;
import cn.memedai.ups.boss.service.context.ThreadLocalContext;
import cn.memedai.ups.boss.service.permission.annotation.Permission;

/**
 * 
 * @descript: 自定义的细粒度权限拦截.
 * @author: chengtx
 * @创建时间: 2016-11-29,下午8:54:35
 */
@Slf4j
public class PermissionInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {

		// 判断该方法是否加了@Permission注解
		if (invocation.getMethod().isAnnotationPresent(Permission.class)) {
			log.info("=== invoke PermissionInterceptor");
			// 得到方法上的Permission注解
			final Permission pm = invocation.getMethod().getAnnotation(Permission.class);
			
			// 获取被注解方法中的request参数，要求方法中一定要有HttpServletRequest参数
			@SuppressWarnings("unchecked")
			final List<String> permissions = (List<String>)ThreadLocalContext.getHttpRequest().getSession().getAttribute(PermissionConstant.ACTIONS_SESSION_KEY);
			if (permissions.contains(pm.value())){ // 拥有此功能点权限
				// 执行被拦截的方法，如果此方法不调用，则被拦截的方法不会被执行
				log.info("== contain permission:" + pm.value());
				return invocation.proceed();
			}
			// 没有此功能点权限
			log.warn("=== 您没有执行此操作的权限:" + pm.value());
			return "permissionError"; // 跳转到错误提示页面.
		}
		// 没加@Permission注解的方法可直接执行
		// 执行被拦截的方法，如果此方法不调用，则被拦截的方法不会被执行
		return invocation.proceed();
	}
}
