package com.enze.job;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryTaskJobTest {
	@Autowired
	QueryTaskJob queryTaskJob;

	@Test
	public void testIn() {
		try {
			queryTaskJob.in();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testOut() {
		fail("Not yet implemented");
	}

}
