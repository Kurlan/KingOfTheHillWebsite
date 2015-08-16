package com.kingofthehill.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.LoadingCache;
import com.kingofthehill.model.Grafiti;

@Service
public class GetLatestGrafitiUtils {

    private final LoadingCache<String, Grafiti> getLatestGrafitiCache;

    @Autowired
    public GetLatestGrafitiUtils(LoadingCache<String, Grafiti> getLatestGrafitiCache) {
        this.getLatestGrafitiCache = getLatestGrafitiCache;
    }

    public Grafiti getLatestGrafiti(String queue) {
        return getLatestGrafitiCache.getUnchecked(queue);
    }
}
