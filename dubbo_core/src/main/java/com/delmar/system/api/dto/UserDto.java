package com.delmar.system.api.dto;

import lombok.Data;

/**
 * Created by admin on 2016/9/6.
 */
@Data
public class UserDto {
    private String username;
    private String name;
    private Integer clientId;
    private Integer orgId;
}
