package com.kingofthehill.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kingofthehill.exceptions.UnableToUploadToS3Exception;
import com.kingofthehill.utils.FileUploadUtil;
import com.kingofthehill.utils.CreateGrafitiUtils;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private final FileUploadUtil fileUploadUtil;
    private final CreateGrafitiUtils grafitiUtils;

    @Autowired
    public FileUploadController(FileUploadUtil fileUploadUtil, CreateGrafitiUtils grafitiUtils) {
        this.fileUploadUtil = fileUploadUtil;
        this.grafitiUtils = grafitiUtils;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(HttpServletRequest request, @RequestParam("title") String title,
            @RequestParam("altText") String altText, @RequestParam(value = "link", required = false) String link,
            @RequestParam("email") String email, @RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        if (file.isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "FILE IS EMPTY");
            return modelAndView;
        }

        try {
            String keyname = fileUploadUtil.uploadFile(file);
            grafitiUtils.createGrafiti(keyname, altText, link, title, email, "FREE");
        } catch (UnableToUploadToS3Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/kingofthehill/");
        return modelAndView;
    }
}
