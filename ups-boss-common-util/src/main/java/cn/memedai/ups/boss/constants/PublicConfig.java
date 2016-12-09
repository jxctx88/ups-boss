/**
 * className：PublicConifg.java <br>
 * @version：1.0  <br>
 * date: 2014-11-5-上午10:15:20     <br>
 * Copyright (c)  2014中益智正公司-版权所有   <br>
 */
package cn.memedai.ups.boss.constants;

import java.util.Map;

import cn.memedai.ups.boss.utils.ResourceUtils;

/**
 * 环境配置基础类 <br>
 * @author tongxiong.cheng
 * @date 2016-11-30 下午2:45:55
 * @version 1.0
 */
public class PublicConfig {

	/**
	 * 用户文件配置 加载。
	 */
	public static Map<String, String> PUBLIC_USER = ResourceUtils.getResource("public_user").getMap();

	/**
	 * 系统文件配置 加载。
	 */
	public static Map<String, String> PUBLIC_SYSTEM = ResourceUtils.getResource("public_system").getMap();
	

	/**
	 * ======================================= URL配置写在上面， 分割线  ========================================
	 */

	/**
	 * 是否开发状态
	 */
	public final static boolean IS_DEV_STATUS = Boolean.parseBoolean(PUBLIC_USER.get("IS_DEV_STATUS"));
	
	/**
	 * 密码错误限制次数
	 */
	public final static Integer PWD_ERROR_LIMIT_TIMES = Integer.parseInt(PUBLIC_USER.get("PWD_ERROR_LIMIT_TIMES"));

	/**
	 * 密码错误限制时间（分钟）
	 */
	public final static Integer PWD_ERROR_LIMIT_TIME = Integer.parseInt(PUBLIC_USER.get("PWD_ERROR_LIMIT_TIME"));

	/**
	 * 密码错误次数值 将 出现验证码，如果值为0 则永远不会出现验证码
	 */
	public final static Integer PWD_TIMES_USE_KAPTCHA = Integer.parseInt(PUBLIC_USER.get("PWD_TIMES_USE_KAPTCHA"));

	/**
	 * 会员同时登录最大限制
	 */
	public final static Integer MEMBER_BIGGEST_LOGINS = Integer.parseInt(PUBLIC_USER.get("MEMBER_BIGGEST_LOGINS"));


	/**
	 * ========================================= 平台信息，分割线 ======================================== 
	 */

	/**
	 * 公司名称
	 */
	public final static String COMPANY_NAME = PUBLIC_USER.get("COMPANY_NAME");

	/**
	 * 公司简称
	 */
	public final static String COMPANY_FOR = PUBLIC_USER.get("COMPANY_FOR");

	/**
	 * 公司LOGO
	 */
	public final static String COMPANY_LOGO = PUBLIC_USER.get("COMPANY_LOGO");

	/**
	 * 公司 ICP
	 */
	public final static String COMPANY_NET_ICP = PUBLIC_USER.get("COMPANY_NET_ICP");

	/**
	 * 公司 Address
	 */
	public final static String COMPANY_ADDRESS = PUBLIC_USER.get("COMPANY_ADDRESS");

	/**
	 * 公司 TEL
	 */
	public final static String COMPANY_TEL = PUBLIC_USER.get("COMPANY_TEL");

	/**
	 * 公司 email
	 */
	public final static String COMPANY_EMAIL = PUBLIC_USER.get("COMPANY_EMAIL");

	/**
	 * 公司 HR email
	 */
	public final static String COMPANY_HR_EMAIL = PUBLIC_USER.get("COMPANY_HR_EMAIL");

	/**
	 * 公司 About us
	 */
	public final static String COMPANY_ABOUT_US = PUBLIC_USER.get("COMPANY_ABOUT_US");

}
