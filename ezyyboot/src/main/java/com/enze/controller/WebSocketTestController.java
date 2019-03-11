package com.enze.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enze.websocket.WebSocketServer;

@Controller
@RequestMapping("/socket")
public class WebSocketTestController {
   @RequestMapping(value="/index",method=RequestMethod.GET)
   public String index() {
	   
	   return "websockettest/index";
   }
   
   @ResponseBody
   @RequestMapping(value="/indexq",method=RequestMethod.GET)
   public String indexq() {
	   
	   try {
		WebSocketServer.sendInfo("123.....");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return "success";
   }
   
}
