<%@page contentType="charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="classes.Employee" %>
<%@ page import="classes.MyControllerDAO" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@page errorPage="error.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>Support page</title>
    </head>
    <body>
        <%
            MyControllerDAO controller = (MyControllerDAO) session.getAttribute("controller");
        %>
        <form method="post">
            Employee name :
            <input type="text" name="ENAME" id="in" value="<%=request.getParameter("ENAME")%>"/>
            <br/>
            <br/>
            <button name="name">
                Ok
            </button>
            <br/>
            <br/>
            <a href="index.html">
                <BIG>Back</BIG>
            </a>
            <br/>
            <br/>
            <table id="table">
                <tr>
                    <th>Employee number</th>
                    <th>Employee name</th>
                    <th>Job</th>
                    <th>Hiredate</th>
                    <th>Departament</th>
                    <th>&#8224;</th>
                </tr>
                <%
                    if (request.getParameter("name") != null) {
                        List<Employee> employees = controller.getEmployeesByName(request.getParameter("ENAME"));
                        Iterator<Employee> iterator = employees.iterator();
                        Employee employee;
                        while (iterator.hasNext()) {
                            employee = iterator.next();
                %>
                <tr>
                    <td><%=employee.getEmployeeNumber()%></td>
                    <td><%=employee.getEmployeeName()%></td>
                    <td><%=employee.getPosition()%></td>
                    <td><%=employee.getHireDate()%></td>
                    <td><%=employee.getDepartmentName()%></td>
                    <td>
                        <a href="javascript:void(0)" onclick="deleteRow(this)">Delete</a>
                    </td>
                </tr>
                <%
                        }

                    }
                %>
            </table>
        </form>
        <script type="text/javascript">
            function deleteRow(r) {
            var isDelete = confirm('Delete?');
            if (isDelete) {
            var i = r.parentNode.parentNode.rowIndex;
            document.getElementById("table").deleteRow(i);
            }
            }
        </script>
    </body>
</html>