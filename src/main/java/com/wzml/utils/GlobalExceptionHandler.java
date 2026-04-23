package com.wzml.utils;

import com.wzml.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//- @RestControllerAdvice表示当前类为全局异常处理器
public class GlobalExceptionHandler {

    //处理异常
    @ExceptionHandler//@ExceptionHandler指定可以捕获哪种类型的异常进行处理
    public Result ex(Exception e){//方法形参中指定能够处理的异常类型
        e.printStackTrace();//打印堆栈中的异常信息
        //捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员");
    }

}