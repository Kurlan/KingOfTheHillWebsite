package com.kingofthehill.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingofthehill.model.Grafiti;

@Component
public class CreateGrafitiRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public CreateGrafitiRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createGrafiti(Grafiti grafiti) {
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(grafiti);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

}
