package com.kingofthehill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingofthehill.utils.AdvanceQueueUtils;

import lombok.extern.apachecommons.CommonsLog;

@Controller
@RequestMapping("/advance")
@CommonsLog
public class AdvanceController {
    
    private final AdvanceQueueUtils advanceQueueUtils;
    
    @Autowired
    public AdvanceController(AdvanceQueueUtils advanceQueueUtils) {
        this.advanceQueueUtils = advanceQueueUtils;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String advance() {
        
        advanceQueueUtils.advanceQueues();
        log.info("Advancing queues");
        return "OK";
    }
}
