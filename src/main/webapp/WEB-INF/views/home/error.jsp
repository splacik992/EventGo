<%--
  Created by IntelliJ IDEA.
  User: pali
  Date: 10.06.2021
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage = "true" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Error Page</title>
</head>
<body>
${pageContext.errorData.throwable.cause}
<% if(response.getStatus() == 500){ %>
<font color="red">Error: <%=exception.getMessage() %></font><br>
<% if(response.getStatus() == 404){ %>
<font color="red">Error: <%=exception.getMessage() %></font><br>

<%-- include login page --%>
<%@ include file="home.jsp"%>
<%}else {%>
Hi There, error code is <%=response.getStatus() %><br>
Wróć do <a href="">STRONA GŁÓWNA</a>
<%} %>
</body>
</html>