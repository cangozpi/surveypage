<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String userFlag = (String)request.getAttribute("userFlag"); %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Register</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body cz-shortcut-listen="true">
<div class="container">
    <form class="form-signin" method="POST" action="/register">
        <h2 class="form-signin-heading">Please sign in</h2>
        <% if(userFlag != null){ %>
            <h3><%=userFlag %></h3>
        <% }%>
        <p>
            <label for="username" class="sr-only">Username</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="" autofocus="">
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="">
        </p>
        <input name="_csrf" type="hidden" value="c2c65336-43c6-46a8-9169-a2f296426657">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form>
</div>
</body></html>