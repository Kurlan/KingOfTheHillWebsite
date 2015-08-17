package com.kingofthehill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kingofthehill.model.ImageQueue;
import com.kingofthehill.utils.ImageQueueUtils;

@Controller
@RequestMapping("/queue")
public class QueueController {

    private final ImageQueueUtils imageQueueUtils;

    @Autowired
    public QueueController(ImageQueueUtils imageQueueUtils) {
        this.imageQueueUtils = imageQueueUtils;
    }

    @RequestMapping(value = "/{queueName}", method = RequestMethod.GET)
    @ResponseBody
    public ImageQueue getQueue(@PathVariable String queueName) {
        return imageQueueUtils.getQueue(queueName);
    }
}
