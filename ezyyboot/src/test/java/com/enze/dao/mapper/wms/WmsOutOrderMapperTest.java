package com.enze.dao.mapper.wms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enze.entity.TaskEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WmsOutOrderMapperTest {
    @Autowired
	WmsOutOrderMapper wmsOutOrderMapper;
    
    @Autowired
    WmsTradeOrderMapper wmsTradeOrderMapper;
    
	@Test
	public void testGetTask() {
		TaskEntity task=wmsOutOrderMapper.getTask();
		System.out.println("task:"+task.toString());
	}
	
	
	@Test
	public void testGetTask2() {
		TaskEntity task=wmsTradeOrderMapper.getTask();
		System.out.println("task:"+task.toString());
	}

}
