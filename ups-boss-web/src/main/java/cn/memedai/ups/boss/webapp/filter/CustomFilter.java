package cn.memedai.ups.boss.webapp.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;

import cn.memedai.ups.boss.constants.PermissionConstant;
import cn.memedai.ups.boss.service.context.ThreadLocalContext;
import cn.memedai.ups.boss.utils.StrUtil;


/**
 * 自定义过滤器.<br/>
 * 1、对静态资源不处理. <br/>
 * 2、要求管理员一定要先登录才能进入后台（对request进行安全过滤），否则跳转到登录页面. <br/>
 * 3、判断系统是否已安装，未安装则跳转到安装页. <br/>
 * 
 * 对Request里的传值参数进行安全过滤，防御XSS攻击.
 * 
 * @author chengtx.
 * @创建时间: 2016-11-26,下午11:05:46
 */
@Component
@Slf4j
public class CustomFilter implements Filter {

	private static ThreadLocal<Map<String, Object>> outPutMsg = new ThreadLocal<Map<String, Object>>();

	/**
	 * 线程绑定，其内容会在outPrint方法调用后清空
	 * 
	 * @return the outputMsg
	 */
	public Map<String, Object> getOutputMsg() {
		Map<String, Object> output = outPutMsg.get();
		if (output == null) {
			output = new HashMap<String, Object>();
			outPutMsg.set(output);
		}
		return output;
	}

	protected void setOutputMsg(String key, String value) {
		getOutputMsg().put(key, value);
	}

	/**
	 * 输出，同时清空outPutMsg
	 * 
	 * @param response
	 * @param result
	 */
	public void outPrint(HttpServletResponse response, Object result) {
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result.toString());
			getOutputMsg().clear();
		} catch (IOException e) {
			log.error("清空本地线程数据异常！",e);
		}
	}

	public void destroy() {
		log.info("=== CustomFilter destroy");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getServletPath(); // 请求路径
		//log.info("=== uri={}",uri);
		//添加TRACE_ID
		if(StringUtils.isBlank(MDC.get("THREAD_ID"))){
			MDC.put("THREAD_ID", StrUtil.get32UUID());
		}
		
		try {
			// 静态资源不处理
			if (uri.startsWith("/statics") || isStatis(uri)) {
				chain.doFilter(request, response);
				return;
			}

			// 判断会话是否过期
			Object operator = request.getSession().getAttribute(PermissionConstant.OPERATOR_SESSION_KEY);
			if (!uri.startsWith("/login") && !uri.startsWith("/posTicket") && !uri.endsWith(".jsp") && operator == null ) {
				// 如果操作员未登录或登录超时，重定向到后台登录界面
				log.info("=======> 请求已拦截 url：" + uri);
				response.sendRedirect(request.getContextPath() + "/login/timeoutConfirm");
				return;
			}

			// 为自定义的上下文工具类设值
			ThreadLocalContext.setHttpRequest(request);
			ThreadLocalContext.setHttpResponse(response);
			// 对Request里的传值参数进行安全过滤
			HttpServletRequest xssRequest = new XssHttpServletRequestWrapper(request);
			// 其他情况
			chain.doFilter(xssRequest, response);
			return;
		} catch (RuntimeException e) {
			log.error("== ", e);
			getOutputMsg().put("STATE", "FAIL");
			getOutputMsg().put("MSG", e.getMessage());
			outPrint(response, JSONObject.fromObject(getOutputMsg()));
		} finally {
			ThreadLocalContext.remove();
		}
	}

	/**
	 * 是否是静态资源
	 * @param uri
	 * @return
	 */
	private boolean isStatis(String uri) {
		// 不过滤的uri
		String noFileter = "htm|html|gif|jpg|jpeg|png|bmp|swf|ioc|rar|zip|txt|flv|mid|doc|ppt|pdf|xls|mp3|wma";
		List<String> noFilterList = Splitter.on("|").splitToList(noFileter);
		for(String str : noFilterList){
			if(uri.endsWith(str)){
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig arg) throws ServletException {
		log.info("====> CustomFilter init");
	}
}
