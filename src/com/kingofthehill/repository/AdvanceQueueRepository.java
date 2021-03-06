package com.kingofthehill.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.model.Grafiti;

@Service
public class AdvanceQueueRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public AdvanceQueueRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setGrafitiToStatus(String grafitiId, String toStatus) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Grafiti grafiti = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Grafiti.class).add(Restrictions.eq("grafitiId", grafitiId))
                    .setMaxResults(1);
            grafiti = (Grafiti) criteria.uniqueResult();
            grafiti.setStatus(toStatus);
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
