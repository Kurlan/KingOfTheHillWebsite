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
public class GetLatestGrafitiRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public GetLatestGrafitiRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Grafiti getLatestGrafiti(String status) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Grafiti grafiti = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Grafiti.class).add(Restrictions.eq("status", status))
                    .setMaxResults(1);
            grafiti = (Grafiti) criteria.uniqueResult();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return grafiti;
    }
}
