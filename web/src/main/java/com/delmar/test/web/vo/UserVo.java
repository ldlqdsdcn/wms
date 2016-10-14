package com.delmar.test.web.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 刘大磊 on 2016/10/12 17:56.
 */
@Data
public class UserVo implements Serializable{
    private String name;
    private String email;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

}
