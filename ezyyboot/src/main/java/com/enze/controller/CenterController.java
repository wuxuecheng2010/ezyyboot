package com.enze.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.enze.entity.Result;
import com.enze.entity.TaskEntity;
import com.enze.service.CenterService;
import com.enze.utils.ResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: CenterController
* @Description: 调度室工作消息面板
* @author wuxuecheng
* @date 2019年3月5日
*
 */
@RequestMapping(value = "/center")
@Controller
@Slf4j
public class CenterController extends BaseController{
	

	@Value(value="${websocket.server}")
	String socketServer;
	
	@Autowired
	CenterService<TaskEntity> centerServiceImpl;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		Map<String, Object> map = new ModelMap();
		map.put("socketServer", socketServer);
		String viewName = "center/index";
		ModelAndView mv = new ModelAndView(viewName, map);
		return mv;
	}
	
	@RequestMapping("/outdata")
	@ResponseBody
	public Result getOutData(){
		List<TaskEntity> list=centerServiceImpl.getCenterOutData();
		this.result=ResultUtil.success(list);
		return this.result;
	}
	
	
	@RequestMapping("/indata")
	@ResponseBody
	public Result getInData(){
		List<TaskEntity> list=centerServiceImpl.getCenterInData();
		this.result=ResultUtil.success(list);
		return this.result;
	}
	
	
	
	
}
