package com.delmar.devs.service.impl;

import com.delmar.devs.model.GenModelDto;
import com.delmar.devs.service.GenCodeService;

import java.io.File;
import java.util.*;

/**
 * Created by admin on 2016/9/6.
 */
public class GenCodeServiceImpl implements GenCodeService {
    private static ResourceBundle resourceBundle;
    private List<GenModelDto> genModelDtoList=new ArrayList<>();
    private Map<String,GenModelDto> childrenMap=new HashMap<>();
    private Map<String,File> outModuleMap=new HashMap<>();

    public void addModel(GenModelDto genModelDto)
    {
        genModelDtoList.add(genModelDto);
    }
    public GenModelDto getGenModelByTableName(String tableName)
    {
        for(GenModelDto genModelDto:genModelDtoList)
        {
            if(genModelDto.getTableName().equals(tableName))
            {
                return genModelDto;
            }
        }
        return null;
    }

    public List<GenModelDto> getGenModelDtoList() {
        return genModelDtoList;
    }

    @Override
    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}
