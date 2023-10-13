<%--
  Created by IntelliJ IDEA.
  User: soheepark
  Date: 2023/10/13
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
%>

<h1>id : <%=id%></h1>
<h1>pwd : <%=pwd%></h1>

</body>
</html>
