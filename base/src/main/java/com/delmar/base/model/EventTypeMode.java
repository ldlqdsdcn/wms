package com.delmar.base.model;

import com.delmar.core.model.CoreModel;

public class EventTypeMode  extends CoreModel{
    
    private Integer eventtypeId;

    private String mode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventtypeId() {
        return eventtypeId;
    }

    public void setEventtypeId(Integer eventtypeId) {
        this.eventtypeId = eventtypeId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }
}