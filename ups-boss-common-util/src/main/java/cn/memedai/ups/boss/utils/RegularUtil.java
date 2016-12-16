package cn.memedai.ups.boss.utils;

import java.util.regex.Pattern;


/**
 * 正则表达式验证
 * @author tongxiong.cheng
 * @date 2016-12-12 下午3:21:51
 * @version 1.0
 */
public class RegularUtil {

	/**
	 * @author shenjialong
	 * @param regular
	 *            正则表达式
	 * @param str
	 *            需要验证的语句
	 * @return 返回boolbean值:匹配返回true
	 */
	public static Boolean validatePattern(String regular, String input) {
		Pattern p = Pattern.compile(regular);
		return p.matcher(input).matches();
	}
}
