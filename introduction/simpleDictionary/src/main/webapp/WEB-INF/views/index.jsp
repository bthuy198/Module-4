<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22/02/2023
  Time: 3:09 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Dictionary</title>
</head>
<body>
<h1>Dictionary</h1>
<form method="post" action="/translate">
  <label>Rate: </label><br/>
  <input type="text" name="english" placeholder="word" /><br/>
  <input type="submit" id="submit" value="Translate"/>
</form>
<h2>Result</h2>
<h1>${result}</h1>
</body>
</html>
