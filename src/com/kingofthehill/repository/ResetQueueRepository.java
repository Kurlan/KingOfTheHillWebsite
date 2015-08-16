package com.kingofthehill.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingofthehill.model.Grafiti;
import com.kingofthehill.model.GrafitiStatus;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class ResetQueueRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ResetQueueRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setAllCompletedToCreated() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        log.info("Bulk updating COMPLETED to CREATED");
        try {
            transaction = session.beginTransaction();
            String hqlUpdate = "update " + Grafiti.class.getName()
                    + " g set g.status = :toStatus where g.status = :fromStatus";
            int updatedEntities = session.createQuery(hqlUpdate)
                    .setString("fromStatus", GrafitiStatus.COMPLETED.getStatus())
                    .setString("toStatus", GrafitiStatus.CREATED.getStatus()).executeUpdate();
            transaction.commit();
            log.info("Updated rows: " + updatedEntities);
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
