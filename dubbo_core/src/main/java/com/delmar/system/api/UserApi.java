package com.delmar.system.api;

import com.delmar.core.api.result.ApiResult;
import com.delmar.system.api.dto.UserDto;

/**
 * Created by admin on 2016/9/6.
 */
public interface UserApi {
    ApiResult<UserDto> verifyToken(String token);
}
