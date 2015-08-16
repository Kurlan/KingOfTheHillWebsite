package com.kingofthehill.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.repository.QueueSizeRepository;

@Service
public class QueueSizeUtils {

    public final QueueSizeRepository queueSizeRepository;
    
    @Autowired
    public QueueSizeUtils(QueueSizeRepository queueSizeRepository) {
        this.queueSizeRepository = queueSizeRepository;
    }
    
    public long getQueueSizeWithStatus(String queueName, String status) {
        return queueSizeRepository.getQueueSizeWithStatus(queueName, status);
    }
}
