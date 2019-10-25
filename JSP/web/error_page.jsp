<%--
  Created by IntelliJ IDEA.
  User: EHOT
  Date: 25-Oct-19
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h3>An error occurred: <%=request.getAttribute("error")%>
</h3>
<br><br><a href="index.jsp">Go back to home</a>
</body>
</html>
