package cn.memedai.ups.boss.service.permission.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.memedai.ups.boss.dal.dao.PmsOperatorLogDOMapper;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDOExample;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsOperatorLogService;

@Service("pmsOperatorLogService")
public class PmsOperatorLogServiceImpl implements PmsOperatorLogService {
	
	@Autowired
	PmsOperatorLogDOMapper pmsOperatorLogDOMapper;

	@Override
	public PageInfo<PmsOperatorLogDO> listPage(PageParam pageParam,
			PmsOperatorLogDOExample example) {
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
		List<PmsOperatorLogDO> list = pmsOperatorLogDOMapper.selectByExample(example);
		return new PageInfo<PmsOperatorLogDO>(list);
	}

}
