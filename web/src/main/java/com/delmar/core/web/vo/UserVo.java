package com.delmar.core.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/13.
 */
@Data
public class UserVo implements Serializable{
    private String name;
    private String email;
}
