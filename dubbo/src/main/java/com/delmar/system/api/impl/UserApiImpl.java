package com.delmar.system.api.impl;

import com.delmar.core.api.def.ErrorCodes;
import com.delmar.core.api.result.ApiResult;
import com.delmar.sys.model.User;
import com.delmar.sys.service.UserService;
import com.delmar.sys.util.JwtUtil;
import com.delmar.system.api.UserApi;
import com.delmar.system.api.dto.UserDto;
import com.delmar.utils.CommonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 刘大磊 on 2016/9/6.
 * dubbo数据交换类
 */
@Slf4j
public class UserApiImpl implements UserApi {
    @Autowired
    UserService userService;
    @Override
    public ApiResult<UserDto> verifyToken(String token) {
        if(!JwtUtil.validateToken(token))
        {
           return ApiResult.fail(ErrorCodes.BUSINESS_EXCEPTION.getCode(),"token验证失败");
        }
        Long userId=(Long)JwtUtil.getJwtClaims(token).getClaimValue("userId");
        User user=userService.getUserById(userId.intValue());
        UserDto userDto=CommonConverter.convertObject(user,UserDto.class);
        return ApiResult.success(userDto);
    }
}
