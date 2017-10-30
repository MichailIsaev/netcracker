<%@ page import="datasource.Message" %>
<%@ page import="datasource.PostgresDataSource" %>
<%@ page import="java.util.List" %>
<%@ page import="datasource.MessageControllerDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <title>Your messages , sir</title>
    <%
        MessageControllerDAO<Message> controller = new PostgresDataSource();
        List<Message> messages;
    %>

</head>

<body>

<form method="get">
    <p><label>Start time : </label><input type="time" name="start"><label> End time : </label>
        <input type="time" name="end"><label> </label><input
                type="submit" name="get_messages"></p>

    <table>
        <%
            if (request.getParameter("get_messages") != null) {
                controller.setUpConnection();
                String start = request.getParameter("start");
                String end = request.getParameter("end");
                messages = controller.getMessagesByInterval(start, end);
                for (Message message : messages) {
        %>
        <tr>
            <td><%=message.getMessage()%>
            </td>
            <td>( <%=message.getTime()%> )
            </td>
        </tr>

        <%
                }
            }
        %>
    </table>
</form>
</body>
<footer>
    <a href="producer.jsp">Back</a>
</footer>
</html>
