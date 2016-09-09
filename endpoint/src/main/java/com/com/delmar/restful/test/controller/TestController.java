package com.com.delmar.restful.test.controller;

import com.delmar.core.api.def.ErrorCodes;
import com.delmar.core.api.result.ApiResult;
import com.delmar.system.api.UserApi;
import com.delmar.system.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "/api/test/getUserInfo",method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<UserDto> getUserInfo(String username)
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
                return ApiResult.success(u);
            }
        }
        return ApiResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(),"找不到对应的用户");
    }
}
