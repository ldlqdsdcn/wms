package com.delmar.test.web.controller;

import com.delmar.core.api.result.ApiResult;
import com.delmar.sys.model.User;
import com.delmar.test.web.vo.UserParam;
import com.delmar.test.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘大磊 on 2016/10/12 17:55.
 */
@Controller
@RequestMapping("/test")
public class CoreTestController {
    @Autowired
    private RedisTemplate<String,List<UserVo>> redisTemplate;
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<UserVo> getUsers()
    {
        List<UserVo> list=  redisTemplate.opsForValue().get("user");
        return list;
    }
    @RequestMapping(value ="/saveUsers",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<UserVo>> saveUsers(UserParam userParam)
    {

        redisTemplate.opsForValue().set("user",userParam.getUserVoList());
        return ApiResult.success(userParam.getUserVoList());
    }
    @RequestMapping(value ="/saveUser",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Void> saveUser(UserVo user)
    {
        List<UserVo> list=  redisTemplate.opsForValue().get("user");
        if(list==null)
        {
            list=new ArrayList<>();
        }
        list.add(user);
        redisTemplate.opsForValue().set("user",list);
        return ApiResult.success(null);
    }





}
