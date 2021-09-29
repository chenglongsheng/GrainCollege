package com.atguigu.commonutils.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chenglongsheng
 * @create 2021-09-29 18:47
 */
@Data
public class UcenterMember {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

}
