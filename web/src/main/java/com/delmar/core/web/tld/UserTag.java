package com.delmar.core.web.tld;

import com.delmar.core.model.EaModelContent;
import com.delmar.core.web.bean.EaContext;
import com.delmar.sys.model.User;
import com.delmar.sys.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by admin on 2016/8/25.
 */
public class UserTag extends BodyTagSupport {
    private Integer userId;
    private String module;
    @Override
    public int doEndTag() throws JspException {
        if(module.equals("user"))
        {
            if(userId==null)
            {
                return super.doEndTag();
            }
            HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
            UserService userService=EaContext.ApplicationContext.getBean(UserService.class);
            User user=userService.selectByPrimaryKey(userId);
            try {
                if(user!=null)
                pageContext.getOut().print(user.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.doEndTag();
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
