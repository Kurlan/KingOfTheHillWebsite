package com.kingofthehill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kingofthehill.model.Grafiti;
import com.kingofthehill.model.ImageQueue;
import com.kingofthehill.utils.ImageQueueUtils;
import lombok.extern.apachecommons.CommonsLog;

@Controller
@RequestMapping("/")
@CommonsLog
public class LatestGrafitiController {

    private static final String CDN_URL = "dk01572ibg6u2.cloudfront.net";
    private final ImageQueueUtils imageQueueUtils;

    @Autowired
    public LatestGrafitiController(ImageQueueUtils imageQueueUtils) {
        this.imageQueueUtils = imageQueueUtils;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView latestGrafiti() {

        ModelAndView modelAndView = new ModelAndView();
        ImageQueue freeQueue = imageQueueUtils.getQueue("FREE");
        ImageQueue paidQueue = imageQueueUtils.getQueue("PAID");
        Grafiti grafiti = freeQueue.getLastGrafiti();
        if (grafiti == null) {
            grafiti = Grafiti.EMPTY;
        }
        modelAndView.addObject("cdnURL", CDN_URL);
        log.info("Latest grafitiId: " + grafiti.getGrafitiId());
        modelAndView.addObject("paidQueueSize", paidQueue.getLength());
        modelAndView.addObject("freeQueue", freeQueue);
        modelAndView.setViewName("latestGrafiti");
        return modelAndView;
    }
}
