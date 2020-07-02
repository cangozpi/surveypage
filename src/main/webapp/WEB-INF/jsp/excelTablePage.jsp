<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Excel Table</title>
    <link rel="stylesheet" href="style/excelTablePage.css">
</head>
<body>

    <table id="excelTable">

        <c:forEach var="id" items="${ids}">
            <tr></tr>
            <tr></tr>
            <tr>
                <td>Survey Name:</td>
                <td>${id.surveyName.substring(0,1).toUpperCase()}${id.surveyName.substring(1)}</td>

                <c:forEach var="question" items="${id.questions}">

                    <td></td>
                    <td>${question.name.substring(0,1).toUpperCase()}${question.name.substring(1)}:</td>
                    <td>${question.answer.substring(0,1).toUpperCase()}${question.answer.substring(1)}</td>

                </c:forEach>

            </tr>

        </c:forEach>

    </table>

<script src="excelTablePage.js"></script>
</body>
</html>