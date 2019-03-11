package com.enze.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enze.entity.TmsTrDoc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TmsTrDocMapperAnnoTest {
	
	@Autowired
	TmsTrDocMapperAnno tmsTrDocMapper;

	@Test
	public void testSelectTmsTrDocByTrid() {
		TmsTrDoc  tmsTrDoc =tmsTrDocMapper.selectTmsTrDocByTrid(267);
		System.out.println(tmsTrDoc.getCredate());
	}

}
