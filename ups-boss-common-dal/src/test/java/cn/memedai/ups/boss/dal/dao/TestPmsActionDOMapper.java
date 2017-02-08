package cn.memedai.ups.boss.dal.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.memedai.ups.boss.dal.dao.common.BaseTest;
import cn.memedai.ups.boss.dal.dao.pms.PmsActionDOMapper;
import cn.memedai.ups.boss.dal.model.pms.PmsActionDO;
import cn.memedai.ups.boss.dal.model.pms.PmsActionDOExample;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class TestPmsActionDOMapper extends BaseTest {

	@Autowired
	PmsActionDOMapper pmsActionDOMapper;
	
	@Test
	public void testSelectByPrimaryKey(){
		PmsActionDO pmsActionDO = pmsActionDOMapper.selectByPrimaryKey(1L);
		System.out.println(pmsActionDO.toString());
	}
	
	@Test
	public void testSlelectByExample(){
		PageHelper.startPage(1, 5);
		PmsActionDOExample example = new PmsActionDOExample();
		example.createCriteria();
		//example.setOrderByClause(" id desc");
		List<PmsActionDO> list = pmsActionDOMapper.selectByExample(example);
		PageInfo<PmsActionDO> pi = new PageInfo<PmsActionDO>(list);
		for(PmsActionDO pms : pi.getList()){
			System.out.println(pms.toString());
		}
		System.out.println(pi.getSize());
		System.out.println(pi.getTotal());
		System.out.println(pi.getEndRow());
		System.out.println(pi.getFirstPage());
		System.out.println(pi.getLastPage());
		System.out.println(pi.getNavigatePages());
		System.out.println(list.size());
	}
	
	
	@Test
	public void testListByExample(){
		PmsActionDO pmsActionDO = new PmsActionDO();
		pmsActionDO.setId(1L);
		List<PmsActionDO> pmsActionDOList = pmsActionDOMapper.listByExample(pmsActionDO);
		for(PmsActionDO onePmsActionDO : pmsActionDOList){
			System.out.println(onePmsActionDO.toString());
		}
	}
	
}

