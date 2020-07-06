<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.questions.model.ApplicationUserModel" %><%
//    ApplicationUserModel user = (ApplicationUserModel) request.getAttribute("user");
%>
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
    <a href="/register" id="singIn">Sign in</a>
    <a href="/login" id="login">Login</a><a href="/logout" id="logOut">LogOut</a>

    <%-- if(user != null && user.getRole().equals("ROLE_ADMIN")) { %>
        <input type="button" value="Admin button" />
    <% } --%>

    <c:if test="${not empty user  and user.role == 'ROLE_ADMIN'}">
        <a href="/adminConsole" id="adminBtn">Admin Console</a>
    </c:if>
</header>
<div class="welcome-page">
    <div class="welcome-script">
                <span class="welcome-border">
                    <h1>Pampering Forms</h1>
                    <h2>fast and easy way to pander your banal instincts</h2>
                </span>
    </div>

    <div class="options">
        <h2 class="questions"><a href="questions.html">Questions</a></h2>
        <h2 class="survey-create"><a href="createSurvey.html">Survey Creation</a></h2>
        <h2 class="answer-survey"><a href="answerSurvey.html?home=true">Answer Survey</a></h2>
        <h2 class="survey-report"><a href="saveSurveyResults.html">Survey Report</a></h2>
    </div>

</div>

<script src="app.js"></script>

</body>
</html>