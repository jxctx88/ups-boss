package cn.memedai.ups.boss.webapp.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 渠道控制类
 * @author: chengtx
 * @创建时间: 2016-12-27,下午9:20:12
 *
 */
@Slf4j
@Controller
@RequestMapping("/channel")
public class ChannelController{
	
	/**
	 * 进入登录页面.
	 * @return
	 */
	@RequestMapping("/listChannel")
	public String listChannel() {
		return "/channel/ChannelList";
	}

}
