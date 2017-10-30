package beans;

import crud.MessageService;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.time.LocalTime;

@MessageDriven(mappedName = "jms/Queue", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "connectionFactory",
                propertyValue = "javax.jms.QueueConnectionFactory")})
public class MyMessageDrivenBean implements MessageListener {

    @Resource
    MessageDrivenContext messageDrivenContext;

    public void ejbCreate() {

    }

    public void setMessageDrivenContext(MessageDrivenContext messageDrivenContext) throws EJBException {
        this.messageDrivenContext = messageDrivenContext;
    }


    public void ejbRemove() throws EJBException {

    }

    public MyMessageDrivenBean() {

    }

    public void onMessage(Message message) {
        try {
            MessageService service = new MessageService();
            entity.Message mail = new entity.Message();
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                mail.setMessage(msg.getText());
                mail.setTime(String.valueOf(LocalTime.now()));

            } else {
                mail.setMessage("Wrong message type!");
                mail.setTime(String.valueOf(LocalTime.now()));
            }
            service.send(mail);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
