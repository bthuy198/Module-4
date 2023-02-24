<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24/02/2023
  Time: 9:04 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sandwich</title>
</head>
<body>
<h1>Sandwich Condiments</h1>
<c:forEach items="${requestScope.condiment}" var="c">
    <h3>${c}</h3>
</c:forEach>
</body>
</html>
