<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.util.Objects" %>
<%@ page import="javax.jms.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <title>MessageSender</title>
    <%
        Context context = new InitialContext();
        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) session.getAttribute("queueCF");
        if (Objects.isNull(queueConnectionFactory)) {
            queueConnectionFactory = (QueueConnectionFactory) context.lookup("jms/QueueConnectionFactory");
            session.setAttribute("queueCF", queueConnectionFactory);
        }
        Queue myQueue = (Queue) session.getAttribute("queue");
        if (Objects.isNull(myQueue)) {
            myQueue = (Queue) context.lookup("jms/Queue");
            session.setAttribute("queue", myQueue);
        }
        QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
        QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = queueSession.createProducer(myQueue);
        if (request.getParameter("send") != null) {
            messageProducer.send(queueSession.createTextMessage(request.getParameter("message")));
        }

    %>
</head>

<body>
<form method="post">
    <script type="application/javascript">
        alert("Input your message ;)");
    </script>
    <label>Your message : </label>
    <input type="text" name="message">
    <input type="submit" name="send" value="Send">
</form>
</body>
<footer>
    <a href="show.jsp">Show your messages?</a>
</footer>
</html>
