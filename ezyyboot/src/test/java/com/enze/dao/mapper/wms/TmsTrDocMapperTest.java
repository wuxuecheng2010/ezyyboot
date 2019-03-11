package com.enze.dao.mapper.wms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enze.entity.TmsTrDoc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TmsTrDocMapperTest {
	
	@Autowired
	TmsTrDocMapper tmsTrDocMapper;

	@Test
	public void testGetOne() {
	 TmsTrDoc tmsTrDoc =tmsTrDocMapper.getOne(229);
	 System.out.println(tmsTrDoc.getCredate()+"*****");
	}

}
