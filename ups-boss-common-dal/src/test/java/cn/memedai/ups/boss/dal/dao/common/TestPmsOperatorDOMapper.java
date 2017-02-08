package cn.memedai.ups.boss.dal.dao.common;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.memedai.ups.boss.dal.dao.pms.PmsOperatorDOMapper;
import cn.memedai.ups.boss.dal.model.pms.PmsOperatorDO;

public class TestPmsOperatorDOMapper extends BaseTest {
	
	@Autowired
	PmsOperatorDOMapper pmsOperatorDOMapper;
	
	@Test
	public void testInsert(){
		PmsOperatorDO pmsOperatorDO = new PmsOperatorDO();
		pmsOperatorDO.setCreatetime(new Date());
		pmsOperatorDO.setIschangedpwd((short) 1);
		pmsOperatorDO.setLastlogintime(new Date());
		pmsOperatorDO.setLoginname("1111");
		pmsOperatorDO.setLoginpwd("1111");
		pmsOperatorDO.setMobileno("123232232");
		pmsOperatorDO.setPwderrorcount((short) 2);
		pmsOperatorDO.setPwderrortime(new Date());
		pmsOperatorDO.setRealname("11");
		pmsOperatorDO.setRemark("eere");
		pmsOperatorDO.setStatus("1");
		pmsOperatorDO.setType("1");
		//pmsOperatorDO.setVersion(0);
		
		pmsOperatorDOMapper.insertSelective(pmsOperatorDO);
		System.out.println("id="+ pmsOperatorDO.getId());
		PmsOperatorDO pmsOperator = pmsOperatorDOMapper.selectByPrimaryKey(pmsOperatorDO.getId());
		System.out.println("version="+ pmsOperator.getVersion());
	}
	
}
