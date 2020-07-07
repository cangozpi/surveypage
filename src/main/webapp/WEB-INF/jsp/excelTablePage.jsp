<%@ page import="com.example.questions.model.AnsweredSurveyModel" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<AnsweredSurveyModel> ids = (List<AnsweredSurveyModel> )request.getAttribute("ids");%>

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

                <td>${id.surveyNamegit}</td>

                <c:forEach var="question" items="${id.questions}">

                    <td></td>
                    <td>${question.name}:</td>
                    <td>${question.answer}</td>

                </c:forEach>

            </tr>

        </c:forEach>

    </table>

<script src="excelTablePage.js"></script>
</body>
</html>