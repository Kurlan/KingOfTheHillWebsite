package com.kingofthehill.cache;

import java.util.List;

import com.google.common.cache.CacheLoader;
import com.kingofthehill.model.Grafiti;
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
        Grafiti grafiti = getLatestGrafitiRepository.getLatestGrafiti(queueName, GrafitiStatus.CURRENT.getStatus());
        if (grafiti == null) {
            grafiti = Grafiti.EMPTY;
        }

        List<Grafiti> lastThree = getLatestGrafitiRepository.getLatestGrafiti(queueName,
                GrafitiStatus.COMPLETED.getStatus(), 3);


        return new ImageQueue(queueName,
                queueSizeRepository.getQueueSizeWithStatus(queueName, GrafitiStatus.CREATED.getStatus()), grafiti,
                lastThree);
    }

}
