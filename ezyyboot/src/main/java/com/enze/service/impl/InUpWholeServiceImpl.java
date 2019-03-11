package com.enze.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.enze.dao.mapper.wms.WmsOutOrderMapper;
import com.enze.dao.mapper.wms.WmsTradeOrderMapper;
import com.enze.dao.mapper.wms.WmsTurnboxMapper;
import com.enze.dao.mapper.wms.WmsStIoMapper;
import com.enze.entity.TaskEntity;
import com.enze.enums.TeskType;
import com.enze.service.TaskService;

/**
 * 
* @ClassName: InUpWholeServiceImpl
* @Description: 整件上架
* @author wuxuecheng
* @date 2019年3月6日
*
 */
@Service
public class InUpWholeServiceImpl implements TaskService<TaskEntity> {

	public static final String key = TeskType.Tesk_WholeInUp.getMsg();

	@Value("${task.timeout.leng}")
	int tasktimeout;
	
	@Autowired
	WmsStIoMapper wmsStIoMapper;

	@Autowired
	RedisTemplate redisTemplate;
	
/*	@Override
	public TaskEntity getTask() {
		TaskEntity taskEntity = (TaskEntity) redisTemplate.opsForValue().get(key);
		if (taskEntity == null) {
			taskEntity = (TaskEntity) wmsStIoMapper.getTask(1,2,2);
			taskEntity.setTaskname(key);
			redisTemplate.opsForValue().set(key, taskEntity);
			redisTemplate.expire(key, 3, TimeUnit.MINUTES);
		}
		return taskEntity;
	}*/
	
	
	
	@Override
	public TaskEntity getTask() {
		TaskEntity taskEntity = (TaskEntity) redisTemplate.opsForValue().get(key);
		if (taskEntity == null) {
			taskEntity = getTaskActual();
			updateCaches(taskEntity,tasktimeout,TimeUnit.MINUTES);
		}
		return taskEntity;
	}

	@Override
	public boolean checkTask() {
		boolean flagupdate=false;//是否更新标识
		TaskEntity taskEntity = (TaskEntity) redisTemplate.opsForValue().get(key);
		if(taskEntity==null) {
			taskEntity=getTask();
			updateCaches(taskEntity,tasktimeout,TimeUnit.MINUTES);
			flagupdate=true;
		}else {
			TaskEntity _taskEntity =  getTaskActual();
			if(!_taskEntity.equals(taskEntity)) {//缓存与实时比较
				updateCaches(_taskEntity,tasktimeout,TimeUnit.MINUTES);
				flagupdate=true;
			}
		}
		return flagupdate;
	}

	@Override
	public TaskEntity getTaskActual() {
		TaskEntity taskEntity = (TaskEntity) wmsStIoMapper.getTask(1,2,2);
		taskEntity.setTaskname(key);
		return taskEntity;
	}

	@Override
	public void updateCaches(TaskEntity taskEntity,int timeout,TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, taskEntity);
		redisTemplate.expire(key, timeout, timeUnit);
	}


	

}






