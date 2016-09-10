package com.delmar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 刘大磊 on 2016/9/10 9:33.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String showHome()
    {
        return "index.html";
    }
}
