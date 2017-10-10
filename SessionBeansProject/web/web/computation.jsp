<%@ page import="calculator.Calculator" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="calculator.CalculatorHome" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$
    </title>
</head>
<body>
<%
    Calculator calculator = (Calculator) session.getAttribute("calculator");
    if (calculator == null) {
        InitialContext context = new InitialContext();
        CalculatorHome calculatorHome = (CalculatorHome) context.lookup("Calculator");
        calculator = calculatorHome.create();
        session.setAttribute("calculator", calculator);
    }
    calculator.setA(0);
    calculator.setB(0);
    if (request.getParameter("a") != null && request.getParameter("b") != null) {
        double a, b;
        a = Double.valueOf(request.getParameter("a"));
        b = Double.valueOf(request.getParameter("b"));
        calculator.setB(b);
        calculator.setA(a);
        if (request.getParameter("operation") != null) {
            char operation = request.getParameter("operation").charAt(0);
            switch (operation) {
                case 'm':
                    calculator.mul();
                    break;
                case 'a':
                    calculator.add();
                    break;
                case 'd':
                    calculator.div();
                    break;
                case 's':
                    calculator.sub();
                    break;
                case 'r':
                    calculator.fromMemoryToOperands();
                    break;
                case 'w':
                    calculator.saveCurrentResultToMemory();
                    break;
                case 'c':
                    calculator.resetInMemory();
                    break;
            }
        }
    }
%>
<form method="post">
    <p><label>First operand : <input name="a" value="<%=calculator.getA()%>"></label></p>
    <p><label>Second operand : <input name="b" value="<%=calculator.getB()%>"></label></p>
    <p><label>Current result : <input name="current"
                                      value="<%=calculator.getCurrentResult()%>"></label>
    </p>
    <p><label>"Memory" : <input name="memory" value="<%=calculator.getMemory()%>"></label></p>
    <p>
        <button name="operation" value="m">mul</button>
        <button name="operation" value="d">div</button>
    </p>
    <p>
        <button name="operation" value="s">sub</button>
        <button name="operation" value="a">sum</button>
    </p>
    <p>
        <button name="operation" value="w">save in memory</button>
        <button name="operation" value="r">read from memory</button>
        <button name="operation" value="c">reset memory</button>
    </p>
</form>


</body>
<footer>
    <a href="index.jsp">Back</a>
</footer>
</html>
