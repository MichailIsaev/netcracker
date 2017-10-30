package beans.mailsenderbean;

import javax.ejb.Remote;
import javax.jms.JMSException;

@Remote
public interface MailSenderRemote {
    void send(String message) throws JMSException;
}
