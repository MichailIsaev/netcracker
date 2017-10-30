package beans.mailreceiverbean;

import entity.Message;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

@Remote
public interface MailReceiverLocal {
    List<Message> getMessages(String start, String end);
}
