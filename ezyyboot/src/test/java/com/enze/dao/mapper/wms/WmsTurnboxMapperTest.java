package com.enze.dao.mapper.wms;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enze.entity.TaskEntity;
@RunWith(SpringRunner.class)
@SpringBootTest
public class WmsTurnboxMapperTest {

	@Autowired
	WmsTurnboxMapper wmsTurnboxMapper;
	
	@Autowired
	WmsStIoMapper wmsWholeboxMapper;
	
	@Test
	public void testGetTaskInt() {
	TaskEntity task=	wmsTurnboxMapper.getTask(1);
	
	System.out.println(task.toString());
	}
	
	
	@Test
	public void testGetTaskInt2() {
	TaskEntity task=	wmsWholeboxMapper.getTask(11,1,1);
	
	System.out.println(task.toString());
	}

}
