package com.delmar.dev;

import com.delmar.devs.model.GenModelDto;
import com.delmar.devs.service.CodeGenerationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class GenerateCodeTest {
    @Autowired
    private CodeGenerationService codeGenerationService;
    @Test
    public void testGenerateCode()
    {
        List<GenModelDto> list=new ArrayList<GenModelDto>();
        list.add(new GenModelDto("core_label","Label","core","标签"));

        codeGenerationService.generateMapperAndModel(list);
    }
}
