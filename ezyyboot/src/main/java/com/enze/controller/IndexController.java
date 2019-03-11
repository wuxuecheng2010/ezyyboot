package com.enze.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/index")
public class IndexController {

	// @ResponseBody
	@RequestMapping("/index")
	public ModelAndView index() {
		Map<String, Object> map = new ModelMap();
		String viewName = "index/index";
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(20);
		list.add(15);
		list.add(7);
		map.put("types", list);
		ModelAndView mv = new ModelAndView(viewName, map);
		int x=1/0;
		return mv;
	}
	
	@RequestMapping("/index1")
	public ModelAndView index1() {
		Map<String, Object> map = new ModelMap();
		String viewName = "index/index";
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(20);
		list.add(15);
		list.add(7);
		map.put("types", list);
		ModelAndView mv = new ModelAndView(viewName, map);
		return mv;
	}
	

	@ResponseBody
	@RequestMapping("/index2")
	public List<String> index2() {
		List<String> list = new ArrayList<String>();
		list.add("下单");
		list.add("拣货中");
		list.add("完成");
		list.add("wx");
		int x=1/0;
		return list;
	}

}
