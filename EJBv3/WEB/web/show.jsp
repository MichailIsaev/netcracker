<%@ page import="beans.mailreceiverbean.MailReceiverLocal" %>
<%@ page import="java.util.Objects" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="entity.Message" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your messages , sir</title>
    <%
        Context context = new InitialContext();
        MailReceiverLocal mailReceiver = (MailReceiverLocal) session.getAttribute("receiver");
        if (Objects.isNull(mailReceiver)) {
            mailReceiver = (MailReceiverLocal) context.lookup("ejb/MailReceiver");
            session.setAttribute("receiver", mailReceiver);
        }
        List<Message> messages = new ArrayList<Message>();

        if (request.getParameter("get_messages") != null) {
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            messages = mailReceiver.getMessages(start, end);
        }
    %>
</head>

<body>

<form method="get">
    <p><label>Start time : </label><input type="time" name="start"><label> End time : </label>
        <input type="time" name="end"><label> </label><input
                type="submit" name="get_messages"></p>

    <table>
        <%
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
        %>
    </table>
</form>
</body>
<footer>
    <a href="producer.jsp">Back</a>
</footer>
</html>
