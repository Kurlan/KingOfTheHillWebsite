package com.kingofthehill.cache;

import com.google.common.cache.CacheLoader;
import com.kingofthehill.model.Grafiti;
import com.kingofthehill.model.GrafitiStatus;
import com.kingofthehill.repository.GetLatestGrafitiRepository;

public class LatestGrafitiCacheLoader extends CacheLoader<String, Grafiti> {

    private final GetLatestGrafitiRepository getLatestGrafitiRepository;

    public LatestGrafitiCacheLoader(GetLatestGrafitiRepository getLatestGrafitiRepository) {
        this.getLatestGrafitiRepository = getLatestGrafitiRepository;
    }

    @Override
    public Grafiti load(String queue) throws Exception {
        return getLatestGrafitiRepository.getLatestGrafiti(queue, GrafitiStatus.CURRENT.getStatus());
    }

}
