package com.delmar.devs.service;

import com.delmar.devs.model.GenModelDto;

import java.util.List;

/**
 * Created by admin on 2016/9/6.
 */
public interface GenCodeService {
    void addModel(GenModelDto genModelDto);
    GenModelDto getGenModelByTableName(String tableName);
    List<GenModelDto> getGenModelDtoList();
    String getMessage(String key);
}
