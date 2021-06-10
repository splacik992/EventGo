<%--
  Created by IntelliJ IDEA.
  User: pali
  Date: 10.06.2021
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html"
         pageEncoding="utf-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Error Page</title>
</head>
<body>
<font color="red" size="10px"> Error: <%=exception.getMessage() %><br>
    Wróć do <a href="/">STRONA GŁÓWNA</a></font><br>
</body>
</html>