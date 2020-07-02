<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Survey Creation</title>
    <link rel="stylesheet" href="style/surveyCreation.css">

</head>
<body>

<div class="mainContainer">

    <h1> &#126; ${surveyName} ${questions.size()} &#126;</h1>
    <hr id="titleTag">

    <div class="questions">
        <c:forEach var="question" items="${questions}">

            <h2>${question.name.substring(0,1).toUpperCase()}${question.name.substring(1)} :</h2>
            <h3>${question.answer}</h3>
            <hr>
        </c:forEach>

    </div>




</div>

</body>
</html>