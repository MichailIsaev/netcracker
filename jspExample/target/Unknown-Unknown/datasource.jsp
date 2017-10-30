<%@ page import="classes.Employee" %>
<%@ page import="classes.MyControllerDAO" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@page errorPage="error.jsp" %>
<%@page contentType="charset=US-ASCII" pageEncoding="US-ASCII" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/dataSourceStyle.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII"/>
        <title>javaEE_task3</title>
    </head>
    <body>
        <%
            MyControllerDAO controller = new MyControllerDAO();
            controller.initialDataSource();
            session.setAttribute("controller", controller);
        %>
        <form name="input" method="post" action="datasource.jsp">
            <b>Employee number :</b>
            <input type="text" name="no" id="input" onblur="isNumber()"/>
            <button name="EMPNO" value="yes" onclick="isNumber()">
                Ok
            </button>
            <br/>
            <br/>
            Employee name :
            <input type="text" id="one" name="name" value="Ivanov" onfocus="document.getElementById('one').value = ''"
                   onblur="if(document.getElementById('one').value == '') document.getElementById('one').value = 'Ivanov'"/>
            <input type="button" name="ENAME" value="Ok" onclick="getName()"/>
            <br/>
            <br/>
            <button name="allEmployees" value="yes">
                Output data of all employees
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
                    if (request.getParameter("allEmployees") != null) {
                        List<Employee> employees = controller.getAllEmployees();
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
                <%
                    if (request.getParameter("EMPNO") != null) {
                        Employee employee = controller.getEmployeeById(Integer.valueOf(request.getParameter("no")));
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
            function isNumber() {
            if (isNaN(document.getElementById("input").value)) {
            alert("Input a number!");
            }
            }
            function getName() {
            if (document.getElementById('one').value != '') {
            location.href = ('supportpage.jsp?ENAME=' +
            document.getElementById('one').value);
            }
            }
        </script>
    </body>
</html>