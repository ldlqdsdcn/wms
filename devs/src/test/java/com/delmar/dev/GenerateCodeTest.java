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
        //子模块1
        GenModelDto line1=  new GenModelDto("b_meeting_participant","MeetingParticipant","cargo","参与人");
        line1.setGenerateService(false);
        list.add(line1);
        //子模块2
        GenModelDto line2=  new GenModelDto("b_meeting_topic","MeetingTopic","cargo","讨论内容");
        line2.setGenerateService(false);
        list.add(line2);
        GenModelDto modelDto=new GenModelDto("b_meeting","Meeting","cargo","会议纪要");
        List<GenModelDto> lineList=new ArrayList<>();
        lineList.add(line1);
        lineList.add(line2);
        modelDto.setIncludeModelList(lineList);
        list.add(modelDto);

        codeGenerationService.generateMapperAndModel(list);
    }
}
