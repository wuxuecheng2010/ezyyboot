package com.enze.dao.mapper.mapserver;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enze.entity.MapAway;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapAwayMapperTest {
	@Autowired
	MapAwayMapper mapAwayMapper;

	@Test
	public void testGetOne() {
		MapAway mapAway= mapAwayMapper.getOne(373);
		System.out.println(mapAway.getDistance());
	}

}
