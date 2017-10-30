<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="sourcebean.DataSource" %>
<%@ page import="employee.Employee" %>

<html lang="en">
<head>
    <title>Support page</title>
</head>
<body>
<%
    DataSource dataSource = (DataSource) session.getAttribute("datasource");
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
                List<Employee> employees = dataSource.getEmployeesByName(request.getParameter("ENAME"));
                Iterator<Employee> iterator = employees.iterator();
                Employee employee;
                while (iterator.hasNext()) {
                    employee = iterator.next();
        %>
        <tr>
            <td><%=employee.getEmployeeNumber()%>
            </td>
            <td><%=employee.getEmployeeName()%>
            </td>
            <td><%=employee.getPosition()%>
            </td>
            <td><%=employee.getHireDate()%>
            </td>
            <td><%=employee.getDepartmentName()%>
            </td>
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
<footer><a href="index.jsp">Back</a>
</footer>
</html>
