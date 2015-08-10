package com.kingofthehill.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView uploadPage() {
        
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.setViewName("uploadPage");
        return modelAndView;
    }
}
