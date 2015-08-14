package com.kingofthehill.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.repository.AdvanceQueueRepository;

@Service
public class AdvanceQueueUtils {

    private final AdvanceQueueRepository advanceQueueRepository;
    
    @Autowired
    public AdvanceQueueUtils(AdvanceQueueRepository advanceQueueRepository) {
        this.advanceQueueRepository = advanceQueueRepository;
    }

    public void advanceQueues() {
        // TODO Auto-generated method stub
        
    }

}
