package com.kingofthehill.cache;

import com.google.common.cache.CacheLoader;
import com.kingofthehill.model.GrafitiStatus;
import com.kingofthehill.model.ImageQueue;
import com.kingofthehill.repository.GetLatestGrafitiRepository;
import com.kingofthehill.repository.QueueSizeRepository;

public class ImageQueueCacheLoader extends CacheLoader<String, ImageQueue> {

    private final GetLatestGrafitiRepository getLatestGrafitiRepository;
    private final QueueSizeRepository queueSizeRepository;

    public ImageQueueCacheLoader(GetLatestGrafitiRepository getLatestGrafitiRepository,
            QueueSizeRepository queueSizeRepository) {
        this.getLatestGrafitiRepository = getLatestGrafitiRepository;
        this.queueSizeRepository = queueSizeRepository;
    }

    @Override
    public ImageQueue load(String queueName) throws Exception {
        return new ImageQueue(queueName,
                queueSizeRepository.getQueueSizeWithStatus(queueName, GrafitiStatus.CREATED.getStatus()),
                getLatestGrafitiRepository.getLatestGrafiti(queueName, GrafitiStatus.CURRENT.getStatus()));
    }

}
