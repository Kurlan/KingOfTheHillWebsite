package com.kingofthehill.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.LoadingCache;
import com.kingofthehill.model.ImageQueue;

@Service
public class ImageQueueUtils {
    private final LoadingCache<String, ImageQueue> imageQueueCache;

    @Autowired
    public ImageQueueUtils(LoadingCache<String, ImageQueue> imageQueueCache) {
        this.imageQueueCache = imageQueueCache;
    }

    public ImageQueue getQueue(String queueName) {
        return imageQueueCache.getUnchecked(queueName);
    }
}
