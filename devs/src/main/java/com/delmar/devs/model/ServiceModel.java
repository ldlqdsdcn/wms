package com.delmar.devs.model;

import lombok.Data;

/**
 * Created by admin on 2016/8/27.
 */
@Data
public class ServiceModel {
    private String model;
    private String module;

    private boolean hasCreated;
    private boolean hasUserId;
    private boolean hasOrgId;
    private boolean hasClientId;
}
