package com.delmar.restful.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 刘大磊 on 2016/9/10 9:52.
 */
@ApiModel
@Data
public class UserVo {
    @ApiModelProperty(value = "登录名", required = true)
    private String username;
    @ApiModelProperty(value = "用户名", required = true)
    private String name;
    @ApiModelProperty(value = "实体Id", required = true)
    private Integer clientId;
    @ApiModelProperty(value = "组织Id", required = true)
    private Integer orgId;
}
