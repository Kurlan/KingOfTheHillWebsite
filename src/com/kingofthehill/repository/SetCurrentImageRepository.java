package com.kingofthehill.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.model.Grafiti;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class SetCurrentImageRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public SetCurrentImageRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setOldestCreatedToCurrent(String fromStatus, String toStatus, String queue) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        DateTime nowUTC = new DateTime(DateTimeZone.UTC).plusSeconds(15);
        log.info(nowUTC.toString());
        Grafiti grafiti = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Grafiti.class).add(Restrictions.eq("queue", queue))
                    .add(Restrictions.eq("status", fromStatus)).addOrder(Order.asc("created")).setMaxResults(1);
            grafiti = (Grafiti) criteria.uniqueResult();
            grafiti.setStatus(toStatus);
            grafiti.setCompleted(nowUTC);
            session.save(grafiti);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

}
