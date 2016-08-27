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
        GenModelDto line1=  new GenModelDto("core_meeting_participant","MeetingParticipant","core","参与人");
        line1.setGenerateService(false);
        list.add(line1);
        GenModelDto line2=  new GenModelDto("core_meeting_topic","MeetingTopic","core","讨论内容");
        list.add(line2);
        line2.setGenerateService(false);
        GenModelDto modelDto=new GenModelDto("core_meeting","Meeting","core","会议");
        List<GenModelDto> lineList=new ArrayList<GenModelDto>();
        lineList.add(line1);
        lineList.add(line2);
        modelDto.setIncludeModelList(lineList);
        list.add(modelDto);

        codeGenerationService.generateMapperAndModel(list);
    }
}
