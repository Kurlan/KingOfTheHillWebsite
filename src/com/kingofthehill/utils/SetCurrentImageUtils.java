package com.kingofthehill.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.repository.QueueSizeRepository;
import com.kingofthehill.repository.ResetQueueRepository;
import com.kingofthehill.repository.SetCurrentImageRepository;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class SetCurrentImageUtils {

    private final SetCurrentImageRepository setCurrentImageRepository;
    private final QueueSizeRepository queueSizeRepository;
    private final ResetQueueRepository resetQueueRepository;

    @Autowired
    public SetCurrentImageUtils(SetCurrentImageRepository setCurrentImageRepository,
            QueueSizeRepository queueSizeRepository, ResetQueueRepository resetQueueRepository) {
        this.setCurrentImageRepository = setCurrentImageRepository;
        this.queueSizeRepository = queueSizeRepository;
        this.resetQueueRepository = resetQueueRepository;
    }

    public void setCurrentImage() {
        long numberCreated = queueSizeRepository.getQueueSizeWithStatus("FREE", "CREATED");
        long numberCompleted = queueSizeRepository.getQueueSizeWithStatus("FREE", "COMPLETED");

        log.info("Number of rows in CREATED: " + numberCreated);
        log.info("Number of rows in COMPLETED: " + numberCompleted);

        if (numberCreated > 0) {
            log.info("Setting oldest created row to current.");
            setCurrentImageRepository.setOldestCreatedToCurrent("CREATED", "CURRENT", "FREE");
            return;
        }

        if (numberCompleted > 0) {
            resetQueueRepository.setAllCompletedToCreated();
            setCurrentImageRepository.setOldestCreatedToCurrent("CREATED", "CURRENT", "FREE");
        }

    }

}
