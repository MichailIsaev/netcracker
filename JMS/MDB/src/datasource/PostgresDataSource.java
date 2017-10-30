package datasource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PostgresDataSource implements MessageControllerDAO<Message> {
    private Connection connection;
    private String query;

    public void setUpConnection() throws SQLException, NamingException {
        Context initContext = new InitialContext();
        javax.sql.DataSource dataSource = (javax.sql.DataSource) initContext.lookup("jdbc/PostgresDB");
        connection = dataSource.getConnection();
    }

    public void addMessage(String message) throws SQLException, NamingException {
        setUpConnection();
        //query = "INSERT INTO message_info (message ,  arrivalTime) values( text ''?'' , (select current_time));";
        query = "INSERT INTO  msg  values(? , ?);";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, message);
        statement.setString(2, String.valueOf(LocalTime.now()));
        boolean check = statement.execute();
        if (!check) throw new SQLException();
        statement.close();
        connection.close();
    }

    public List<Message> getMessagesByInterval(String start, String end) throws SQLException, NamingException {
        setUpConnection();
        List<Message> messages = new ArrayList<>();
        query = "SELECT msg , time FROM msg WHERE (? <=  time and time  <= ?);";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, start);
        statement.setString(2, end);
        boolean check = statement.execute();
        if (!check) throw new SQLException();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            Message message = new Message(resultSet.getString(1), resultSet.getString(2));
            messages.add(message);
        }
        statement.close();
        connection.close();
        return messages;
    }

}
