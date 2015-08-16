package com.kingofthehill.cache;

import com.google.common.cache.CacheLoader;
import com.kingofthehill.model.GrafitiStatus;
import com.kingofthehill.repository.QueueSizeRepository;

public class QueueLengthCacheLoader extends CacheLoader<String, Long> {

    private final QueueSizeRepository queueSizeRepository;

    public QueueLengthCacheLoader(QueueSizeRepository queueSizeRepository) {
        this.queueSizeRepository = queueSizeRepository;
    }

    @Override
    public Long load(String queue) throws Exception {
        return queueSizeRepository.getQueueSizeWithStatus(queue, GrafitiStatus.CREATED.getStatus());
    }

}
