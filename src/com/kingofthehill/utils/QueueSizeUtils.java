package com.kingofthehill.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.LoadingCache;

@Service
public class QueueSizeUtils {
    public final LoadingCache<String, Long> queueSizeCache;

    @Autowired
    public QueueSizeUtils(LoadingCache<String, Long> queueSizeCache) {
        this.queueSizeCache = queueSizeCache;
    }

    public long getQueueLength(String queueName) {
        return queueSizeCache.getUnchecked(queueName);
    }
}
