<%@page errorPage="error.jsp" %>
<%@page contentType="charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/style.css"/>
    </head>
    <body>
        <form method="post" action="computation.jsp">
            <div id="ftext">First argument :</div>
            <input type="text" name="first" id="f"
                   value="<%=(request.getParameter("first") != null) ?request.getParameter("first") : "0"%>"/>
            <div id="stext">Second argument :</div>
            <input type="text" name="second" id="s"
                   value="<%=(request.getParameter("second") != null) ?request.getParameter("second") : "0"%>"/>
            <button name="operation" value="m" id="mult">
                mult
            </button>
            <button name="operation" value="d" id="div">
                divide
            </button>
            <button name="operation" value="a" id="sum">
                sum
            </button>
            <button name="operation" value="s" id="sub">
                sub
            </button>
        </form>

        <%
            double result = 0;
            String operation = request.getParameter("operation");
            String a = request.getParameter("first");
            String b = request.getParameter("second");
            if (operation != null && a != "" && b != "") {
                double x = Double.valueOf(a);
                double y = Double.valueOf(b);
                switch (operation) {
                    case "m":
                        result = x * y;
                        break;
                    case "d":
                        result = x / y;
                        if (y == 0) throw new Exception();
                        break;
                    case "a":
                        result = x + y;
                        break;
                    case "s":
                        result = x - y;
                        break;
                }
            }
        %>

        <div id="restext">Result :
            <input type="text" name="result" id="result" value="<%=result %>"/>
            <br/>
            <a href="index.html">
                <BIG>Back</BIG>
            </a>
        </div>

    </body>
</html>