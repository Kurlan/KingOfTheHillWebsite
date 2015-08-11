package com.kingofthehill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kingofthehill.model.Grafiti;
import com.kingofthehill.utils.GetLatestGrafitiUtils;

import lombok.extern.apachecommons.CommonsLog;

@Controller
@RequestMapping("/")
@CommonsLog
public class LatestGrafitiController {

    private static final String CDN_URL = "dk01572ibg6u2.cloudfront.net";
    private final GetLatestGrafitiUtils getLatestGrafitiUtils;

    @Autowired
    public LatestGrafitiController(GetLatestGrafitiUtils getLatestGrafitiUtils) {
        this.getLatestGrafitiUtils = getLatestGrafitiUtils;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView latestGrafiti() {

        ModelAndView modelAndView = new ModelAndView();
        Grafiti grafiti = getLatestGrafitiUtils.getLatestGrafiti();
        modelAndView.addObject("grafiti", grafiti);
        modelAndView.addObject("cdnURL", CDN_URL);
        log.info("Latest grafitiId: " + grafiti.getGrafitiId());
        modelAndView.setViewName("latestGrafiti");
        return modelAndView;
    }

    @RequestMapping(value = "ajax/grafiti/latest", method = RequestMethod.GET)
    @ResponseBody
    public Grafiti getLatestGrafitiAJAX() {
        return getLatestGrafitiUtils.getLatestGrafiti();
    }

}
