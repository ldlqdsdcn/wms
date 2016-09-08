package com.com.delmar.server.ws.test.impl;

import com.com.delmar.server.ws.test.UserListWs;
import com.delmar.core.api.result.ApiResult;
import com.delmar.system.api.UserApi;
import com.delmar.system.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by admin on 2016/9/8.
 */
@WebService(endpointInterface = "com.com.delmar.server.ws.test.UserListWs")
public class UserListWsImpl implements UserListWs{

    private UserApi userApi;
    @Override
    public List<UserDto> getUserList() {
        ApiResult<List<UserDto>> result=userApi.getUserList();
        return result.getData();
    }

    @Override
    public String getHello(String name) {
        System.out.println("-------------------------->"+name);
        return "Hello"+name;
    }
    public void setUserApi(UserApi userApi) {
        this.userApi = userApi;
    }

}
