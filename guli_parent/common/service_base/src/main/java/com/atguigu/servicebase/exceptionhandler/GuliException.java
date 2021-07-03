package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenglongsheng
 * @create 2021-07-03 21:19
 */
@Data
@AllArgsConstructor// 有参构造
@NoArgsConstructor// 无参构造
public class GuliException extends RuntimeException {

    private Integer code;// 状态码
    private String msg;// 异常信息

}
