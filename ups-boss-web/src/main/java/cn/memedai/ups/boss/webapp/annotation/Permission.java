package cn.memedai.ups.boss.webapp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action层权限配置注解.<br/>
 * 模块标识和功能点标识共同构成唯一权限点.
 * @author tongxiong.cheng
 * @date 2016-11-29 下午4:31:02
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME) //声明注解的保留期限
@Target(ElementType.METHOD) // 声明可以使用该注解的目标类型为在方法中使用
@Documented
public @interface Permission {
	/** 权限值 */
	String value(); // 注解成员
	
}
