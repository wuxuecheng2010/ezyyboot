package com.enze.dao.mapper.erp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enze.entity.TbProductinfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TbProductinfoMapperTest {
	@Autowired
	TbProductinfoMapper tbProductinfoMapper;
	
	@Test
	public void testGetOne() {
		TbProductinfo tbProductinfo=tbProductinfoMapper.getOne(1670);
		System.out.println(tbProductinfo.getVcproductname());
	}

}
