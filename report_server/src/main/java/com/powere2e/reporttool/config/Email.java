/**
 * @File: Email.java 
 *
 */
package com.powere2e.reporttool.config;

public class Email
{
    private String emailserver;
    
    private String mailfromaddress;
    
    private String mailsubject;
    
    private String mailbody;
    
    private String mailuser;
    
    private String mailpassword;

    public String getEmailserver ()
    {
        return emailserver;
    }

    public void setEmailserver (String emailserver )
    {
        this.emailserver = emailserver;
    }

    public String getMailbody ()
    {
        return mailbody;
    }

    public void setMailbody (String mailbody )
    {
        this.mailbody = mailbody;
    }

    public String getMailfromaddress ()
    {
        return mailfromaddress;
    }

    public void setMailfromaddress (String mailfromaddress )
    {
        this.mailfromaddress = mailfromaddress;
    }

    public String getMailpassword ()
    {
        return mailpassword;
    }

    public void setMailpassword (String mailpassword )
    {
        this.mailpassword = mailpassword;
    }

    public String getMailsubject ()
    {
        return mailsubject;
    }

    public void setMailsubject (String mailsubject )
    {
        this.mailsubject = mailsubject;
    }

    public String getMailuser ()
    {
        return mailuser;
    }

    public void setMailuser (String mailuser )
    {
        this.mailuser = mailuser;
    }
    
    
}
