<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>

<%@ page import="java.util.Objects" %>
<%@ page import="beans.mailsenderbean.MailSenderRemote" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Context context = new InitialContext();
        MailSenderRemote mailSender = (MailSenderRemote) session.getAttribute("sender");
        if (Objects.isNull(mailSender)) {
            mailSender = (MailSenderRemote) context.lookup("ejb/MailSender");
            session.setAttribute("sender", mailSender);
        }

        if (request.getParameter("send") != null) {
            mailSender.send(request.getParameter("message"));
        }

    %>
    <title>$Title$</title>
</head>
<body>
<form method="get">
    <label>Message : </label> <input name="message">
    <input type="submit" name="send">
</form>
</body>
<footer>
    <a href="show.jsp">Show your messages?</a>
</footer>
</html>
