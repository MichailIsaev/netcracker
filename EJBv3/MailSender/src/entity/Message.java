package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class Message implements Serializable {

    @Column(name = "msg")
    private String message;

    public void setTime(String time) {
        this.time = time;
    }

    @Id
    @Column(name = "time")
    private String time;


    public Message() {

    }

    public Message(String message, String time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
