package com.enze.dao.mapper.pms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enze.entity.EpOrder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EpOrderMapperTest {

	@Autowired
	EpOrderMapper epOrderMapper;
	
	@Test
	public void testGetOne() {
		EpOrder epOrder=epOrderMapper.getOne(133);
		System.out.println(epOrder.getAddress());
	}

}
