package com.enze.job;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.enze.enums.MessageType;
import com.enze.service.TaskService;
import com.enze.service.impl.InUpBulkServiceImpl;
import com.enze.service.impl.InUpWholeServiceImpl;
import com.enze.service.impl.OutBulkServiceImpl;
import com.enze.service.impl.OutOrderServiceImpl;
import com.enze.service.impl.OutReplenishmentServiceImpl;
import com.enze.service.impl.OutWholeServiceImpl;
import com.enze.websocket.WebSocketServer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QueryTaskJob {
	
	@Autowired
	InUpBulkServiceImpl inUpBulkServiceImpl;
	@Autowired
	InUpWholeServiceImpl inUpWholeServiceImpl;

	@Autowired
	OutBulkServiceImpl outBulkServiceImpl;
	@Autowired
	OutOrderServiceImpl outOrderServiceImpl;
	@Autowired
	OutReplenishmentServiceImpl outReplenishmentServiceImpl;
	@Autowired
	OutWholeServiceImpl outWholeServiceImpl;

  @Scheduled(initialDelay = 2000,fixedRate = 15000)
  public void in() throws Exception{
	  doWork(MessageType.M_In);
  }
  
  @Scheduled(initialDelay = 4000,fixedRate = 15000)
  public void out() throws Exception{
	  doWork(MessageType.M_Out);
  }
  
  //获取服务列表
  private List<TaskService> getTaskServiceListIn(){
	  List<TaskService> list=new ArrayList<TaskService>();
	  list.add(inUpBulkServiceImpl);
	  list.add(inUpWholeServiceImpl);
	  return list;
  }
  

  //获取服务列表
  private List<TaskService> getTaskServiceListOut(){
	  List<TaskService> list=new ArrayList<TaskService>();
	  list.add(outBulkServiceImpl);
	  list.add(outOrderServiceImpl);
	  list.add(outReplenishmentServiceImpl);
	  list.add(outWholeServiceImpl);
	  return list;
  }
  
  private void doWork(MessageType messageType)throws Exception  {
	  List<TaskService> list=new  ArrayList<TaskService>();
	  switch (messageType.getCode()) {
			case 1:
				list=getTaskServiceListIn();
				break;
			case 2:
				list=getTaskServiceListOut();
				break;
			default:
				break;
			}
	 
	  boolean flagupdate=false;
	  for(TaskService<TaskService> task:list) {
		  boolean f=task.checkTask();
		  flagupdate=flagupdate||f;
	  }
	  if(flagupdate) {
		  //通知客户端
		  WebSocketServer.sendInfo(""+messageType.getCode());
	  }
  }
  
  
  
}
