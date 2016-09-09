package com.delmar.server.ws.test;

import com.delmar.system.api.dto.UserDto;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by admin on 2016/9/8.
 */
@WebService
public interface UserListWs {
     List<UserDto> getUserList();

     public String getHello(String name);
}
