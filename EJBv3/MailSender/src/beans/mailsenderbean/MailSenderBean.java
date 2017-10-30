package beans.mailsenderbean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Stateless
public class MailSenderBean implements MailSenderRemote {

    @Resource(mappedName = "jms/QueueConnectionFactory")
    QueueConnectionFactory queueConnectionFactory;

    @Resource(mappedName = "jms/Queue")
    Queue queue;

    public MailSenderBean() {
    }

    public void send(String message) throws JMSException {
        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
        QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = queueSession.createProducer(queue);
        messageProducer.send(queueSession.createTextMessage(message));
    }

    public void ejbCreate() {
    }
}
