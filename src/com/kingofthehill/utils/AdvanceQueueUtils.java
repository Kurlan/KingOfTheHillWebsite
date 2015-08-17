package com.kingofthehill.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.model.Grafiti;
import com.kingofthehill.model.GrafitiStatus;
import com.kingofthehill.repository.AdvanceQueueRepository;
import com.kingofthehill.repository.GetLatestGrafitiRepository;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class AdvanceQueueUtils {

    private final AdvanceQueueRepository advanceQueueRepository;
    private final SetCurrentImageUtils setCurrentImageUtils;
    private final GetLatestGrafitiRepository getLatestGrafitiRepository;

    @Autowired
    public AdvanceQueueUtils(AdvanceQueueRepository advanceQueueRepository, SetCurrentImageUtils setCurrentImageUtils,
            GetLatestGrafitiRepository getLatestGrafitiRepository) {
        this.advanceQueueRepository = advanceQueueRepository;
        this.setCurrentImageUtils = setCurrentImageUtils;
        this.getLatestGrafitiRepository = getLatestGrafitiRepository;
    }

    public void advanceQueues() {
        Grafiti latest = getLatestGrafitiRepository.getLatestGrafiti("FREE", GrafitiStatus.CURRENT.getStatus());

        if (latest == null) {
            setCurrentImageUtils.setCurrentImage();
            return;
        }

        DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        if (latest.getCompleted().isBefore(nowUTC)) {
            log.info("Current grafiti needs to be updated");
            advanceQueueRepository.setGrafitiToStatus(latest.getGrafitiId(), GrafitiStatus.COMPLETED.getStatus());
            setCurrentImageUtils.setCurrentImage();
        }

    }

}
