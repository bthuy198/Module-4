<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22/02/2023
  Time: 11:49 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Convert currency</title>
</head>
<body>
<h1>Convert currency</h1>
<form method="post" action="/convert">
    <label>Rate: </label><br/>
    <input type="text" name="rate" placeholder="RATE" value="22000"/><br/>
    <label>USD: </label><br/>
    <input type="text" name="usd" placeholder="USD" value="0"/><br/>
    <input type="submit" id="submit" value="Converter"/>
</form>
<h2>Result</h2>
<h1>${result}</h1>
</body>
</html>
