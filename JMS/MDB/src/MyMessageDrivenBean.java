import datasource.PostgresDataSource;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import java.sql.SQLException;

public class MyMessageDrivenBean implements MessageDrivenBean, MessageListener {
    MessageDrivenContext messageDrivenContext;

    public void ejbCreate() {

    }

    public void setMessageDrivenContext(MessageDrivenContext messageDrivenContext) throws EJBException {
        this.messageDrivenContext = messageDrivenContext;
    }


    public void ejbRemove() throws EJBException {

    }

    public void onMessage(Message message) {
        try {
            PostgresDataSource dataSource = new PostgresDataSource();
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                dataSource.addMessage(msg.getText());

            } else {
                dataSource.addMessage("Wrong message type!");
            }
        } catch (JMSException | SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
