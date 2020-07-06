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
    <title>Document</title>
</head>
<body>

    <h1>Admin Console</h1>

    <tr>
        <th>username</th>
        <th>Delete</th>
    </tr>
    
<%
    for(int i=0; i < users.size(); i++) {
%>
    <% if(users.get(i).getRole() != "ROLE_ADMIN") {%>

        <tr>
            <td><%= users.get(i).getUserName() %><td>
            <td><% out.print(i); %></td>
        </tr>
    <% }%>

    <%
    }
%>
    <c:forEach items="${users}" var="user">

        <tr>
            <td>${user.getUserName()}<td>
            <td>deleteIcon</td>
        </tr>

    </c:forEach>
</body>
</html>