package com.enze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enze.controller.advice.ExceptionHandle;
import com.enze.entity.Result;
import com.enze.entity.UserInfo;
import com.enze.enums.ExceptionEnum;
import com.enze.utils.ResultUtil;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ExceptionHandle exceptionHandle;

    /**
     * 返回体测试
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/getResult",method = RequestMethod.GET)
    public Result getResult(@RequestParam("name") String name, @RequestParam("pwd") String pwd){
        Result result = ResultUtil.success();
        //try {
            if (name.equals("zzp")){
                result =  ResultUtil.success(new UserInfo());
            }else if (name.equals("pzz")){
                result =  ResultUtil.error(ExceptionEnum.USER_NOT_FIND);
            }else{
                int i = 1/0;
            }
        //}catch (Exception e){
        //    result =  exceptionHandle.exceptionGet(e);
       // }
        return result;
    }
}
