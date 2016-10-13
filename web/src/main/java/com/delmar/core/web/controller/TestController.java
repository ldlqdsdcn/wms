package com.delmar.core.web.controller;

import com.delmar.core.api.result.ApiResult;
import com.delmar.core.web.vo.UserVo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

@Controller
public class TestController {
    private Logger logger= Logger.getLogger(TestController.class);
    @RequestMapping(value = "/core/addUsers",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<UserVo>> saveUser(@RequestBody List<UserVo> userVoList)
    {
        logger.info(userVoList);
        return ApiResult.success(userVoList);
    }
}
