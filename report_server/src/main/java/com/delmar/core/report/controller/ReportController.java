package com.delmar.core.report.controller;

import com.delmar.core.api.result.ApiResult;
import com.delmar.system.api.UserApi;
import com.delmar.system.api.dto.UserDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/9/6.
 */
@Controller
public class ReportController {
    @Autowired(required = false)
    UserApi userApi;
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(String name)
    {
        Gson gson=new Gson();
      ApiResult<UserDto> result= userApi.verifyUser("allen");
        if(result.isSuccess())
        {
           return gson.toJson(result.getData());
        }
        return result.getMessage();
    }
}
