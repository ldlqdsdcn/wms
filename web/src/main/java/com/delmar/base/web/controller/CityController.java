package com.delmar.base.web.controller;

import com.delmar.base.model.City;
import com.delmar.base.service.CityService;
import com.delmar.base.service.CityTrlService;
import com.delmar.base.web.vo.CityVo;
import com.delmar.core.api.result.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘大磊 on 2016/9/21 9:36.
 */
@Controller
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityTrlService cityTrlService;
    @RequestMapping(value = "/base/getCityList",method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CityVo>> getCityList(String cityName)
    {

       List<City> cityList= cityService.getCityListByName(cityName);
        if(cityList.size()>10)
        {
            cityList=cityList.subList(0,10);
        }
        List<CityVo> cityVoList=new ArrayList<>();
        for(City city:cityList)
        {
            CityVo cityVo=new CityVo();
            cityVo.setId(city.getId());
            cityVo.setCode(city.getCode());
            cityVo.setName(city.getCname());
            cityVoList.add(cityVo);
        }
        return ApiResult.success(cityVoList);
    }
}
