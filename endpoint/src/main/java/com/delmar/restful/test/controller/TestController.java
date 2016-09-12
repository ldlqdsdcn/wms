package com.delmar.restful.test.controller;

import com.delmar.core.api.def.ErrorCodes;
import com.delmar.core.api.result.ApiResult;
import com.delmar.restful.vo.UserVo;
import com.delmar.system.api.UserApi;
import com.delmar.system.api.dto.UserDto;
import com.delmar.utils.CommonConverter;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2016/9/8.
 */
@Controller
public class TestController {
    @Autowired(required = false)
    private UserApi userApi;
    @ApiOperation(value = "根据用户名获取用户对象", httpMethod = "GET", response = ApiResult.class, notes = "根据用户名获取用户对象",produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/api/test/getUserInfo",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody

    public ApiResult<UserVo> getUserInfo(@ApiParam(required = true, name = "username", value = "用户登录名")String username)
    {


        if(StringUtils.isEmpty(username))
        {
            ApiResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(),"用户名不允许为空");
        }
        ApiResult<List<UserDto>> userDtoList=userApi.getUserList();
        for(UserDto u:userDtoList.getData())
        {
            if(u.getUsername().equals(username))
            {
                UserVo userVo= CommonConverter.convertObject(u,UserVo.class);
                return ApiResult.success(userVo);
            }
        }
        return ApiResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(),"找不到对应的用户");
    }
}
