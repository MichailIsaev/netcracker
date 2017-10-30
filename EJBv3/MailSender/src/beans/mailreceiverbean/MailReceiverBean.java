package beans.mailreceiverbean;


import entity.Message;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@NamedQuery(name = "getMessagesByInterval", query = "SELECT msg FROM Message msg WHERE msg.time <=:end AND :start <= msg.time")
@Stateless
public class MailReceiverBean implements MailReceiverLocal {

    @PersistenceContext
    private EntityManager entityManager = Persistence.createEntityManagerFactory("entitiesPU").createEntityManager();

    public MailReceiverBean() {
    }

    public List<Message> getMessages(String start, String end) {
        List<Message> messages;
        Query query = entityManager.createQuery( "SELECT msg FROM Message msg WHERE msg.time <=:end AND :start <= msg.time");
        query.setParameter("end" , end);
        query.setParameter("start" , start);
        messages = query.getResultList();
        return messages;
    }
}
