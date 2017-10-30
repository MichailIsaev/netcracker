package crud;

import entity.Message;

import javax.persistence.*;

public class MessageService {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entitiesPU");

    public void send(Message message) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(message);
        entityManager.getTransaction().commit();
    }

}
