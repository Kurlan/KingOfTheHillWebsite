package com.kingofthehill.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.model.Grafiti;
import com.kingofthehill.repository.AdvanceQueueRepository;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class AdvanceQueueUtils {

    private final AdvanceQueueRepository advanceQueueRepository;
    private final SetCurrentImageUtils setCurrentImageUtils;
    private final GetLatestGrafitiUtils getLatestGrafitiUtils;

    @Autowired
    public AdvanceQueueUtils(AdvanceQueueRepository advanceQueueRepository, SetCurrentImageUtils setCurrentImageUtils,
            GetLatestGrafitiUtils getLatestGrafitiUtils) {
        this.advanceQueueRepository = advanceQueueRepository;
        this.setCurrentImageUtils = setCurrentImageUtils;
        this.getLatestGrafitiUtils = getLatestGrafitiUtils;
    }

    public void advanceQueues() {
        Grafiti latest = getLatestGrafitiUtils.getLatestGrafiti();

        if (latest == null) {
            setCurrentImageUtils.setCurrentImage();
            return;
        }

        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        if (latest.getCompleted().isBefore(nowUTC)) {
            log.info("Current grafiti needs to be updated");
            advanceQueueRepository.setGrafitiToStatus(latest.getGrafitiId(), "COMPLETED");
            setCurrentImageUtils.setCurrentImage();
        }

    }

}
