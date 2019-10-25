<%--
  Created by IntelliJ IDEA.
  User: EHOT
  Date: 25-Oct-19
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Be greeted =)</title>
</head>
<body>

<jsp:useBean id="names" class="beans.NameSupplier" scope="application"/>
<%= session.getAttribute("greeting") == null ? "Hello, " : session.getAttribute("greeting")%><%=names.next()%><br><br>

Select politeness mode:<br>
<form method="post" action="Controller">
    <input type="radio" name="politeness" value="informal">Informal<br>
    <input type="radio" name="politeness" value="normal">Normal<br>
    <input type="radio" name="politeness" value="polite">Polite<br>
    <input type="radio" name="politeness" value="incorrect mode">Incorrect mode<br>

    <input type="submit" value="Update the greeting!">
</form>
</body>
</html>
