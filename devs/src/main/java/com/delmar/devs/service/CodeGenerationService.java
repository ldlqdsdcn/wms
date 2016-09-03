package com.delmar.devs.service;

import com.delmar.devs.model.GenModelDto;

import java.util.List;

/**
 * Created by admin on 2016/8/26.
 */
public interface CodeGenerationService {
    /**
     *
     * @param genModelDtoList
     */
    void generateMapperAndModel(List<GenModelDto> genModelDtoList);
}
