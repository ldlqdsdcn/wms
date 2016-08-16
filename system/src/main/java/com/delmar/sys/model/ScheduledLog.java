package com.delmar.sys.model;

import java.util.Date;

import com.delmar.core.model.CoreModel;

public class ScheduledLog extends CoreModel{

    private Integer scheduledId;

    private Date runTime;

    private Integer result;

    private String msg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScheduledId() {
        return scheduledId;
    }

    public void setScheduledId(Integer scheduledId) {
        this.scheduledId = scheduledId;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }
}