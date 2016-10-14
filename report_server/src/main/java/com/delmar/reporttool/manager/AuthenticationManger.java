package com.delmar.reporttool.manager;

import com.delmar.core.api.result.ApiResult;
import com.delmar.system.api.UserApi;
import com.delmar.system.api.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/9/7.
 */
@Slf4j
@Repository
public class AuthenticationManger {
    @Autowired(required = false)
    private UserApi userApi;

    public boolean verifyUser(String token)
    {
        if(userApi==null)
        {
            log.error("dubbo privider 对象访问失败");
            return false;
        }
        ApiResult<UserDto> apiResult= userApi.verifyToken(token);
        return apiResult.isSuccess();
    }

}
