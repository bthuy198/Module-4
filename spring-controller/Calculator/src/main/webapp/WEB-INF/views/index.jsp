<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24/02/2023
  Time: 10:22 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Calculator</h1>
<form method="get" action="result">
    <input name="a" value="">
    <input name="b" value=""><br>
    <input type="submit" name="cal" value="Addition">
    <input type="submit" name="cal" value="Minus">
    <input type="submit" name="cal" value="Multiplication">
    <input type="submit" name="cal" value="Division">
</form>
<h3>Result: </h3>
<p>${result}</p>
</body>
</html>
