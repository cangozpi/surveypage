<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.questions.model.ApplicationUserModel" %>

<% List<ApplicationUserModel> users = (List<ApplicationUserModel>) request.getAttribute("users");%>


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="style/adminConsole.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Admin Console</title>
</head>
<body>

    <h1>Admin Console</h1>

    <table>
        <tr>
            <th>username</th>
            <th>Delete</th>
        </tr>

    <%
        for(int i=0; i < users.size(); i++) {
    %>
            <%
                if(users.get(i).getRole().equals("ROLE_ADMIN") == false) {
            %>
                <tr>
                    <td><%= users.get(i).getUserName() %></td>
                    <td><i class="fa fa-trash" aria-hidden="true"></i></td>
                </tr>
            <%
            }
            %>

        <%
        }
    %>

    </table>

    <script src="/adminConsole.js"></script>
</body>
</html>