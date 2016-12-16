package cn.memedai.ups.boss.dal.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.memedai.ups.boss.dal.dao.common.BaseTest;
import cn.memedai.ups.boss.dal.model.PmsActionDO;
import cn.memedai.ups.boss.dal.model.PmsActionDOExample;
import cn.memedai.ups.boss.dal.model.PmsMenuDO;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TestPmsMenuDOMapper extends BaseTest {

	@Autowired
	PmsMenuDOMapper pmsMenuDOMapper;
	
	@Test
	public void testListMenuByOperatorId(){
		List<PmsMenuDO> pmsMenuDOList =  pmsMenuDOMapper.listMenuByOperatorId(1);
		System.out.println(pmsMenuDOList.size());
		for(PmsMenuDO pmsMenuDO : pmsMenuDOList){
			System.out.println(pmsMenuDO.toString());
		}
	}
	
}

