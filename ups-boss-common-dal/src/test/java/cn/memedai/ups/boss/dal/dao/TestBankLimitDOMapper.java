package cn.memedai.ups.boss.dal.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.memedai.ups.boss.dal.dao.common.BaseTest;
import cn.memedai.ups.boss.dal.dao.pay.BankLimitDOMapper;
import cn.memedai.ups.boss.dal.model.pay.BankLimitDO;
import cn.memedai.ups.boss.dal.model.pay.BankLimitDOExample;

public class TestBankLimitDOMapper extends BaseTest {

	@Autowired
	BankLimitDOMapper bankLimitDOMapper;
	
	@Test
	public void testSelectByExample(){
		BankLimitDOExample example = new BankLimitDOExample();
		example.createCriteria();//.andIdEqualTo(1L);
		List<BankLimitDO> list = bankLimitDOMapper.selectByExample(example);
		for(BankLimitDO bankLimitDO : list){
			System.out.println(bankLimitDO.toString());
		}
	}
	
}

