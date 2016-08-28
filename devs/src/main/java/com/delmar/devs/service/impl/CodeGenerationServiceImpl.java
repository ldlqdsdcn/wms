package com.delmar.devs.service.impl;

import com.delmar.core.api.ApiResult;
import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.core.service.TableService;
import com.delmar.devs.*;
import com.delmar.devs.ftl.MapperGenerateStrategy;
import com.delmar.devs.ftl.ModelGenerateStrategy;
import com.delmar.devs.model.GenModelDto;
import com.delmar.devs.service.CodeGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2016/8/26.
 */
@Service("codeGenerationService")
public class CodeGenerationServiceImpl implements CodeGenerationService {
    @Autowired
    private TableService tableService;
    public void generateMapperAndModel(List<GenModelDto> genModelDtoList) {
        String[] modelList=new String[genModelDtoList.size()];
        int i=0;
        for(GenModelDto model:genModelDtoList)
        {

            ApiResult<TableMetaDataDto> apiResult= tableService.getTableDescription(model.getTableName());
            TableMetaDataDto tableMetaDataDto= apiResult.getData();
            MapperGenerateStrategy mapperGenerateStrategy=new MapperGenerateStrategy(tableMetaDataDto,model);
            //生成mysql映射文件
            mapperGenerateStrategy.generateMapper();
            ModelGenerateStrategy modelGenerateStrategy=new ModelGenerateStrategy(tableMetaDataDto,model);
            //生成model类
            modelGenerateStrategy.generateModel();
            //生成dao
            GenerateDaoMain generateDaoMain=new GenerateDaoMain(model.getModule(),new String[]{model.getModelName()});
            generateDaoMain.generateInterface();
            generateDaoMain.generatedaoclass();
            //生成service
            if(model.isGenerateService())
            {
                GenerateServiceMain generateServiceMain=new GenerateServiceMain(tableMetaDataDto,model);
                generateServiceMain.generateInterface();
                generateServiceMain.generateServiceclass();
                if(model.isGenerateWeb())
                {
                    //生成 Struts action
                    GenerateActionMain generateActionMain=new GenerateActionMain( model,tableMetaDataDto);
                    generateActionMain.generateActionclass();
                    //生成 jsp
                    GenerateJspPageMain generateJspPageMain=new GenerateJspPageMain(model,"刘大磊",model.getModule(),tableMetaDataDto,tableService);
                    generateJspPageMain.generateJspPage();
                    modelList[i++]=model.getModelName();
                }

            }


        }
        //生成 struts config
        GenerateStrutsConfigMain generateStrutsConfigMain=new GenerateStrutsConfigMain(genModelDtoList.get(0).getModule(),modelList);
        generateStrutsConfigMain.generateStrutsConfig();
    }
}
