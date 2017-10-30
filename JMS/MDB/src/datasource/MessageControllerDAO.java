package datasource;


import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface MessageControllerDAO<T> {

    void setUpConnection() throws SQLException, NamingException;

    void addMessage(String name) throws SQLException, NamingException;

    List<T> getMessagesByInterval(String start, String end) throws SQLException, NamingException;

}