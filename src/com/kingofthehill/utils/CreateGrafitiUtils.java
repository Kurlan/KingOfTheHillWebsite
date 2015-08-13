package com.kingofthehill.utils;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingofthehill.model.Grafiti;
import com.kingofthehill.repository.CreateGrafitiRepository;

@Component
public class CreateGrafitiUtils {

    private static final String CREATED = "CREATED";
    private final CreateGrafitiRepository grafitiRepository;

    @Autowired
    public CreateGrafitiUtils(CreateGrafitiRepository grafitiRepository) {
        this.grafitiRepository = grafitiRepository;
    }

    public String createGrafiti(String s3Key, String altText, String urlLink, String title, String email, String queue) {
        String grafitiId = UUID.randomUUID().toString();
        Date createdTime = null;
        Date modifiedTime = null;
        Grafiti grafiti = new Grafiti(grafitiId, s3Key, altText, urlLink, title, email, CREATED, createdTime,
                modifiedTime, queue);
        grafitiRepository.createGrafiti(grafiti);
        return grafitiId;
    }

}
