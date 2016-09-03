package com.delmar.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by admin on 2016/8/24.
 */
@Controller

public class WindowController {
    @RequestMapping("/core/createWindow")
    public ModelAndView crateWindow()
    {
        return new ModelAndView("/core/windowAddByWizard.jsp");
    }
}
