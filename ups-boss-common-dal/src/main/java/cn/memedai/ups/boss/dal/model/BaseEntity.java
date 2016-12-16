package cn.memedai.ups.boss.dal.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @descript: 基础实体类，包含各实体公用属性 .
 * @author: chengtx
 * @创建时间: 2016-11-26,下午10:18:32
 *
 */
@Setter
@Getter
@ToString
public class BaseEntity {

	private Long id;
	
	private Integer version = 0;
	/**
	 * 创建时间
	 */
	protected Date createtime = new Date();

}
