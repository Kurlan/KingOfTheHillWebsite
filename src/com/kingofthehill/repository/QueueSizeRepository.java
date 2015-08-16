package com.kingofthehill.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.model.Grafiti;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class QueueSizeRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public QueueSizeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public long getQueueSizeWithStatus(String queueName, String status) {
        Session session = sessionFactory.openSession();
        log.info("GETTING from database queue lengths");
        try {
            return (long) session.createCriteria(Grafiti.class).add(Restrictions.eq("queue", queueName))
                    .add(Restrictions.eq("status", status)).setProjection(Projections.rowCount()).uniqueResult();
        } finally {
            session.close();
        }
    }
}
