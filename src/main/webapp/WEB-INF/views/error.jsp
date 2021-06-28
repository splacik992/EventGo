<%--
  Created by IntelliJ IDEA.
  User: pali
  Date: 10.06.2021
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; UTF8"
         pageEncoding="UTF-8" isErrorPage="true" %>

<%@ include file="/WEB-INF/views/home/home.jsp" %>
<!DOCTYPE html>
<html>
<header>

    <h1>Coś poszło nie tak!</h1>
    <h2><span style="color: red; "> Error: <%=exception.getMessage() %><br></h2>
    <h4>Wróć do strony głównej</h4>


</header>
<body>

<a href="/">
    <button class="btn btn-outline-primary">Wróć</button>
</a>
</body>
</html>