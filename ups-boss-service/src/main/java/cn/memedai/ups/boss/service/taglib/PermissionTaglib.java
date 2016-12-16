package cn.memedai.ups.boss.service.taglib;

import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

import cn.memedai.ups.boss.constants.PermissionConstant;
import cn.memedai.ups.boss.service.context.ThreadLocalContext;

/**
 * 自定义权限权标签 .
 * @author tongxiong.cheng
 * @date 2016-12-12 下午3:47:36
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PermissionTaglib extends BodyTagSupport {
	
	private String value; //权限值

	@Override
	public int doStartTag() throws JspException {
		if (StringUtils.isNotBlank(value)){
			@SuppressWarnings("unchecked")
			final List<String> permissions = (List<String>) ThreadLocalContext.getHttpRequest().getSession().getAttribute(PermissionConstant.ACTIONS_SESSION_KEY);
			if (permissions.contains(value.trim())){ // 拥有此功能点权限
				return EVAL_BODY_INCLUDE;
			}
		}
		return SKIP_BODY;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	} 
}
