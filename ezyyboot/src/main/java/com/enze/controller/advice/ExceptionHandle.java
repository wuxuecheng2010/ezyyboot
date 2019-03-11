package com.enze.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateInputException;

import com.enze.controller.exception.DescribeException;
import com.enze.entity.Result;
import com.enze.enums.ExceptionEnum;
import com.enze.utils.ResultUtil;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

	//private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);

	  /**
	   * 判断错误是否是已定义的已知错误，不是则由未知错误代替，同时记录在log中
	   * @param e
	   * @return
	   */
	  @ExceptionHandler(value = Exception.class)
	  @ResponseBody
	  public Result exceptionGet(Exception e){
	      if(e instanceof DescribeException){
	          DescribeException MyException = (DescribeException) e;
	          return ResultUtil.error(MyException.getCode(),MyException.getMessage());
	      }

	      log.error("【系统异常】{}",e);
	      return ResultUtil.error(ExceptionEnum.UNKNOW_ERROR);
	  }


}




