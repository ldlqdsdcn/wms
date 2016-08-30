package com.delmar.core.web.tld;

import com.delmar.core.model.EaModelContent;
import com.delmar.core.web.bean.EaContext;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;
import com.delmar.sys.service.ClientService;
import com.delmar.sys.service.OrgService;
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
    UserService userService=EaContext.ApplicationContext.getBean(UserService.class);
    ClientService clientService=EaContext.ApplicationContext.getBean(ClientService.class);
    OrgService orgService=EaContext.ApplicationContext.getBean(OrgService.class);
    @Override
    public int doEndTag() throws JspException {
        if(userId==null)
        {
            return super.doEndTag();
        }
        if(module.equals("user"))
        {

            User user=userService.selectByPrimaryKey(userId);
            try {
                if(user!=null)
                pageContext.getOut().print(user.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(module.equals("client"))
        {
            Client client=clientService.selectByPrimaryKey(userId);
            try {
                if(client!=null)
                    pageContext.getOut().print(client.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(module.equals("org"))
        {
            Org org=orgService.selectByPrimaryKey(userId);
            try {
                if(org!=null)
                    pageContext.getOut().print(org.getName());
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
