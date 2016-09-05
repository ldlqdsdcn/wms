package com.delmar.utils.model;

import lombok.Data;

/**
 * Created by admin on 2016/9/5.
 */
@Data
public class UserVo {
    private String username;

    private String password;

    private String name;

    private String email;
    private String telephone;
    private byte[] userPic;
    private Integer managerId;
    private String managerName;
    private Integer userTypeId;
    private String remark;
}
