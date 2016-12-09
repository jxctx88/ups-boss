package cn.memedai.ups.boss.dal.dao.common;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.memedai.ups.boss.dal.dao.PmsActionDOMapper;
import cn.memedai.ups.boss.dal.model.PmsActionDO;

public class TestPmsActionDOMapper extends BaseTest {

	@Autowired
	PmsActionDOMapper pmsActionDOMapper;
	
	@Test
	public void testSelectByPrimaryKey(){
		PmsActionDO pmsActionDO = pmsActionDOMapper.selectByPrimaryKey(1L);
		/*StackTraceElement[] trace =Thread.currentThread().getStackTrace();
		for(StackTraceElement rr : trace){
			System.out.println(rr.toString());
		}*/
		System.out.println(pmsActionDO.toString());
	}
	
}
