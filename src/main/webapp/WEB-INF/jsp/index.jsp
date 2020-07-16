<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.questions.model.ApplicationUserModel" %>
<%ApplicationUserModel user = (ApplicationUserModel) request.getAttribute("user");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Future of Forms is Here</title>
    <link rel="stylesheet" href="/style/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital,wght@1,300&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Piedra&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Pangolin&display=swap" rel="stylesheet">
</head>
<body>
<header>

    <% if(user == null){ %>

    <a href="/login" id="login">Login</a>
    <a href="/register" id="singIn">Sign in</a>


    <% } %>

    <%-- if(user != null && user.getRole().equals("ROLE_ADMIN")) { %>
        <input type="button" value="Admin button" />
    <% } --%>

    <c:if test="${not empty user  and user.role == 'ROLE_ADMIN'}">
        <a href="/adminConsole" id="adminBtn">Admin Console</a>
    </c:if>


    <% if(user != null) { %>
        <a href="/logout" id="logOut">LogOut</a>
    <% } %>


</header>

<div class="mainContainer">
    <div class="pattern"></div>
    <h1 class="Pampering-Forms">Pampering Forms</h1>
    <h2 class="welcome-text">Fast and easy way to pander your banal instincts </h2>

    <div class="options">
        <button class="questions"><a href="questions.html" class="Questions">Questions</a></button>
        <button class="survey-create"><a href="createSurvey.html" class="Survey-Creation">Survey Creation</a></button>
        <button class="answer-survey"><a href="answerSurvey.html?home=true" class="Answer-Survey">Answer Survey</a></button>
        <button class="survey-report"><a href="saveSurveyResults.html" class="Survey-Report">Survey Report</a></button>
    </div>

</div>


<script src="app.js"></script>

</body>
</html>