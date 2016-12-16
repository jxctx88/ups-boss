package cn.memedai.ups.boss.webapp.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.memedai.ups.boss.constants.GlobalConstant;
/**
 * 在线人数统计
 * @author tongxiong.cheng
 * @date 2016-12-13 上午9:43:27
 * @version 1.0
 */
public class OnlineUserListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		GlobalConstant.CURRENT_LOGIN_COUNT++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		GlobalConstant.CURRENT_LOGIN_COUNT--;
	}

}
