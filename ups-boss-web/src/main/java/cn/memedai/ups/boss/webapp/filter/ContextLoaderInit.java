package cn.memedai.ups.boss.webapp.filter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.memedai.ups.boss.constants.PublicConfig;

/**
 * 
 * @descript:启动时加载方法
 * @author: chengtx
 * @创建时间: 2016-11-26,下午10:43:04
 *
 */
public class ContextLoaderInit extends HttpServlet {

	private static final long serialVersionUID = 7017571374655597934L;

	// 启动加载,初始化平台系统参数
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		// 公司信息
		context.setAttribute("COMPANY_NAME", PublicConfig.COMPANY_NAME);
		context.setAttribute("COMPANY_FOR", PublicConfig.COMPANY_FOR);
		context.setAttribute("COMPANY_LOGO", PublicConfig.COMPANY_LOGO);
		context.setAttribute("COMPANY_TEL", PublicConfig.COMPANY_TEL);
		context.setAttribute("COMPANY_ADDRESS", PublicConfig.COMPANY_ADDRESS);
		context.setAttribute("COMPANY_EMAIL", PublicConfig.COMPANY_EMAIL);
		context.setAttribute("COMPANY_NET_ICP", PublicConfig.COMPANY_NET_ICP);
		context.setAttribute("COMPANY_HR_EMAIL", PublicConfig.COMPANY_HR_EMAIL);
	}

}
