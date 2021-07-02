package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author chenglongsheng
 * @create 2021-07-02 21:55
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody //返回数据
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("启用了全局异常处理");
    }

}
