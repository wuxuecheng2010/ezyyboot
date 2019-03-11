package com.enze.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enze.dao.mapper.wms.WmsOutOrderMapper;
import com.enze.entity.TaskEntity;
import com.enze.service.CenterService;

@Service
public class CenterServiceImpl implements CenterService {

	@Autowired
	OutOrderServiceImpl outOrderServiceImpl;
	@Autowired
	OutReplenishmentServiceImpl outReplenishmentServiceImpl;
	@Autowired
	OutBulkServiceImpl outBulkServiceImpl;
	@Autowired
	OutWholeServiceImpl outWholeServiceImpl;
	
	@Autowired
	InUpBulkServiceImpl inUpBulkServiceImpl;
	@Autowired
	InUpWholeServiceImpl inUpWholeServiceImpl;
	@Override
	public List<TaskEntity> getCenterOutData() {
		List<TaskEntity> list = new ArrayList<TaskEntity>();
		//下单
		TaskEntity outOrderTaskEntity = outOrderServiceImpl.getTask();
		//补货
		TaskEntity tradeOrderTaskEntity=outReplenishmentServiceImpl.getTask();
		//零星拣货
		TaskEntity turnboxTaskEntity=outBulkServiceImpl.getTask();
		//整件拣货
		TaskEntity wholeboxTaskEntity=outWholeServiceImpl.getTask();
		list.add(outOrderTaskEntity);
		list.add(tradeOrderTaskEntity);
		list.add(turnboxTaskEntity);
		list.add(wholeboxTaskEntity);
		return list;
	}

	@Override
	public List<TaskEntity> getCenterInData() {
		List<TaskEntity> list = new ArrayList<TaskEntity>();
		//散件上架
		TaskEntity inUpBulkTaskEntity=inUpBulkServiceImpl.getTask();
		//整件上架
		TaskEntity inUpWholeTaskEntity=inUpWholeServiceImpl.getTask();
		list.add(inUpBulkTaskEntity);
		list.add(inUpWholeTaskEntity);
		return list;
	}

}
