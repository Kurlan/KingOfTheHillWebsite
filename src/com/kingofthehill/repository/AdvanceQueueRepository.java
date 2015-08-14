package com.kingofthehill.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvanceQueueRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public AdvanceQueueRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
