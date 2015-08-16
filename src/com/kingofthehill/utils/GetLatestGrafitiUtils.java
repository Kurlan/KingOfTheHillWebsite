package com.kingofthehill.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.model.Grafiti;
import com.kingofthehill.model.GrafitiStatus;
import com.kingofthehill.repository.GetLatestGrafitiRepository;

@Service
public class GetLatestGrafitiUtils {

    private final GetLatestGrafitiRepository getLatestGrafitiRepository;

    @Autowired
    public GetLatestGrafitiUtils(GetLatestGrafitiRepository getLatestGrafitiRepository) {
        this.getLatestGrafitiRepository = getLatestGrafitiRepository;
    }

    public Grafiti getLatestGrafiti(String queue) {
        return getLatestGrafitiRepository.getLatestGrafiti(queue, GrafitiStatus.CURRENT.getStatus());
    }
}
