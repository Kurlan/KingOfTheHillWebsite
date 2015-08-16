package com.kingofthehill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kingofthehill.model.Grafiti;
import com.kingofthehill.model.QueueLengths;
import com.kingofthehill.utils.GetLatestGrafitiUtils;
import com.kingofthehill.utils.QueueSizeUtils;
import com.kingofthehill.model.GrafitiStatus;

import lombok.extern.apachecommons.CommonsLog;

@Controller
@RequestMapping("/")
@CommonsLog
public class LatestGrafitiController {

    private static final String CDN_URL = "dk01572ibg6u2.cloudfront.net";
    private final GetLatestGrafitiUtils getLatestGrafitiUtils;
    private final QueueSizeUtils queueSizeUtils;

    @Autowired
    public LatestGrafitiController(GetLatestGrafitiUtils getLatestGrafitiUtils, QueueSizeUtils queueSizeUtils) {
        this.getLatestGrafitiUtils = getLatestGrafitiUtils;
        this.queueSizeUtils = queueSizeUtils;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView latestGrafiti() {

        ModelAndView modelAndView = new ModelAndView();
        Grafiti grafiti = getLatestGrafitiUtils.getLatestGrafiti("FREE");
        if (grafiti == null) {
            grafiti = Grafiti.EMPTY;
        }
        modelAndView.addObject("grafiti", grafiti);
        modelAndView.addObject("cdnURL", CDN_URL);
        log.info("Latest grafitiId: " + grafiti.getGrafitiId());
        modelAndView.addObject("freeQueueSize",
                queueSizeUtils.getQueueSizeWithStatus("FREE", GrafitiStatus.CREATED.getStatus()));
        modelAndView.addObject("paidQueueSize",
                queueSizeUtils.getQueueSizeWithStatus("PAID", GrafitiStatus.CREATED.getStatus()));
        modelAndView.setViewName("latestGrafiti");
        return modelAndView;
    }

    @RequestMapping(value = "ajax/grafiti/latest", method = RequestMethod.GET)
    @ResponseBody
    public Grafiti getLatestGrafitiAJAX() {
        return getLatestGrafitiUtils.getLatestGrafiti("FREE");
    }

    @RequestMapping(value = "ajax/queueLengths", method = RequestMethod.GET)
    @ResponseBody
    public QueueLengths getQueueLengthsAJAX() {
        return new QueueLengths(queueSizeUtils.getQueueSizeWithStatus("FREE", GrafitiStatus.CREATED.getStatus()),
                queueSizeUtils.getQueueSizeWithStatus("PAID", GrafitiStatus.CREATED.getStatus()));
    }

}
