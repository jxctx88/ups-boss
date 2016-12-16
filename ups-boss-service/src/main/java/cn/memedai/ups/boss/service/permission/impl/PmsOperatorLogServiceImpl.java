package cn.memedai.ups.boss.service.permission.impl;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.memedai.ups.boss.dal.dao.PmsOperatorLogDOMapper;
import cn.memedai.ups.boss.dal.model.PmsOperatorDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDO;
import cn.memedai.ups.boss.dal.model.PmsOperatorLogDOExample;
import cn.memedai.ups.boss.enums.OperatorLogStatusEnum;
import cn.memedai.ups.boss.enums.OperatorLogTypeEnum;
import cn.memedai.ups.boss.service.page.PageParam;
import cn.memedai.ups.boss.service.permission.PmsOperatorLogService;

@Service("pmsOperatorLogService")
@Slf4j
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

	@Override
	public PmsOperatorLogDO getById(Long id) {
		return pmsOperatorLogDOMapper.selectByPrimaryKey(id);
	}

	@Override
	public void createOperatorLog(OperatorLogTypeEnum logTypeEnum,
			OperatorLogStatusEnum logStatusEnum, String content,
			PmsOperatorDO operator, String ipAddr) {
		if (operator == null) {
			log.warn("==> operator is null");
			return;
		}
		
		PmsOperatorLogDO optLog = new PmsOperatorLogDO();
		optLog.setOperatorid(operator.getId());
		optLog.setOperatorname(operator.getLoginname());
		optLog.setOperatetype((short)logTypeEnum.getValue());
		optLog.setStatus((short)logStatusEnum.getValue());
		optLog.setIp(ipAddr);
		optLog.setContent(content);
		optLog.setCreatetime(new Date());
		pmsOperatorLogDOMapper.insert(optLog);
		
	}

}
