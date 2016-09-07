/**
 * @File: DataSource.java 
 *
 */
package com.powere2e.reporttool.config;

public class DataSource
{
    private String key;
    
    private boolean isdefault;
    
    private String type;
    
    private String JNDIName;
    
    private String jdbcdriver;
    
    private String jdbcurl;
    
    private String username;
    
    private String password;

    public boolean isIsdefault ()
    {
        return isdefault;
    }

    public void setIsdefault (boolean isdefault )
    {
        this.isdefault = isdefault;
    }

    public String getJdbcdriver ()
    {
        return jdbcdriver;
    }

    public void setJdbcdriver (String jdbcdriver )
    {
        this.jdbcdriver = jdbcdriver;
    }

    public String getJdbcurl ()
    {
        return jdbcurl;
    }

    public void setJdbcurl (String jdbcurl )
    {
        this.jdbcurl = jdbcurl;
    }

    public String getJNDIName ()
    {
        return JNDIName;
    }

    public void setJNDIName (String name )
    {
        JNDIName = name;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key )
    {
        this.key = key;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password )
    {
        this.password = password;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type )
    {
        this.type = type;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username )
    {
        this.username = username;
    }
    
    
    
}
