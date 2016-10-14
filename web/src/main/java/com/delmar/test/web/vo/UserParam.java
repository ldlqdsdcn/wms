package com.delmar.test.web.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by 刘大磊 on 2016/10/13 16:53.
 */
@Data
public class UserParam implements java.io.Serializable {
    private List<UserVo> userVoList;
}
