<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="stylesheet" href="datepicker.min.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Event GO !</title>

    <!-- Bootstrap core CSS -->
    <script src="<c:url value="/vendor/bootstrap/js/index.js"/>"></script>
    <link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/theme/css/blog-home.css"/>" rel="stylesheet">
    <link href="<c:url value="/vendor/bootstrap/css/index.css"/>" rel="stylesheet">

    <link href="" rel="stylesheet">

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">

    <div class="container">
        <a class="navbar-brand" href="/">Event GO
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <span style="color: chartreuse;">
        <sec:authorize access="isAuthenticated()">
            ( <sec:authentication property="principal.username"/> )
        </sec:authorize>
            </span>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                </li>

                <sec:authorize access="hasRole('ADMIN')">
                    <a class="nav-link" href="/admin/events">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Admin Console</span>
                    </a>
                </sec:authorize>

                <sec:authorize access="hasRole('USER')">
                    <a class="nav-link" href="/user/events">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>User Console</span>
                    </a>
                </sec:authorize>

                <li class="nav-item">
                    <a class="nav-link" href="/login">Zaloguj</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/register">Rejestracja</a>
                </li>
                <a class="nav-link">
                    <sec:authorize access="isAuthenticated()">
                        <form action="<c:url value="/logout"/>" method="post">
                            <input type="submit" value="Wyloguj">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </sec:authorize>
                </a>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container">
    <sec:authorize access="isAuthenticated()">

    <div id="wrapper">
        <!-- Sidebar -->


        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/">
                <div class="sidebar-brand-icon rotate-n-15">
                </div>

            </a>
            <!-- Divider -->
            <hr class="sidebar-divider my-0">
            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <button class="open-button" onclick="function openForm() {
                        document.getElementById('myForm').style.display = 'block';
                    }
                    openForm()">Utwórz Wydarzenie
                </button>

                <button class="open-button">Moje wydarzenie
                </button>

                <a href="/event/all">
                    <button class="open-button">Visit Google</button>
                </a>
            </li>
            <div class="form-popup" id="myForm">
                <table class="table">
                    <h2>Nowe wydarzenie</h2>
                    <tr>
                        <form:form method="post" modelAttribute="event" action="/event">
                        <td>
                            <label>
                                Nazwa Wydarzenia: <form:input path="name"/>
                                <form:errors path="name" cssClass="error"/>

                            </label>
                            <br>
                            <label>
                                Miejsce Wydarzenia: <form:input path="localization.name"/>
                                <form:errors path="localization.name" cssClass="error"/>
                            </label>
                            <br>
                            <label>
                                Adress: <form:input path="localization.adress"/>
                                <form:errors path="localization.adress" cssClass="error"/>
                            </label>
                            <br><label>
                            Miasto: <form:input path="localization.city"/>
                            <form:errors path="localization.city" cssClass="error"/>
                        </label>
                            <br>
                            <label>
                                Kategoria: <form:select path="categories" items="${categ}" multiple="false"
                                                        itemLabel="name"
                                                        itemValue="id"/>
                            </label>
                            <br>
                            <label>
                                Data wydarzenia: <input type="date" name="eventDate" id="datepicker"/>
                                <form:errors path="eventDate" cssClass="error"/>
                            </label>
                            <br>
                            <label>
                                Opis:
                                <form:textarea path="description" cssClass="description"/>
                                <form:errors path="description" cssClass="error"/>
                            </label>
                        </td>
                    </tr>


                </table>
                <form:button type="submit" class="btn btn-outline-primary">Utwórz</form:button>
                <form:button type="button" class="btn btn-outline-primary" onclick="function closeForm() {
               document.getElementById('myForm').style.display = 'none';
            }
            closeForm()">Zamknij
                </form:button>
                </form:form>
        </ul>
        <hr class="sidebar-divider d-none d-md-block">

        <c:if test="${not empty param.error}">
        <h>Złe dane!</h>
        </c:if>
        </sec:authorize>
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <div class="row">
                <!-- User panel Row -->
            </div>

            <div class="container">

                <div class="row">

                    <!-- Blog Entries Column -->
                    <div class="col-md-8">
                        <h1 class="my-4">hmm..
                            <small>What to do today ?</small>
                        </h1>
                        <c:forEach items="${events}" var="eve">
                            <!-- Blog Post -->
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h2 class="card-title">${eve.name} - ${eve.eventDate}</h2>
                                    <p class="card-text">${eve.description}</p>
                                    <a href="/event/details/${eve.id}" class="btn btn-primary">Więcej &rarr;</a>
                                </div>
                                <div class="card-footer text-muted">
                                    <strong><p class="card-title"><a>${eve.localization.city}</a></p>
                                    </strong>


                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <%--        <!-- Sidebar Widgets Column -->--%>
                    <div class="col-md-4">
                        <!-- Search Widget -->
                        <form:form action="/event" method="post" modelAttribute="event">
                            <div class="card my-4">
                                <h5 class="card-header">Wyszukaj po nazwie..</h5>
                                <div class="card-body">
                                    <div class="input-group">
                                        <input type="text" name="name" class="form-control" placeholder="Search for...">
                                        <span class="input-group-append">
                        <button class="btn btn-secondary" type="submit">Go!</button>
                      </span>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                        <div class="card my-lg-n1">
                            <h5 class="card-header">Kategoria</h5>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-10">
                                        <ul class="list-unstyled mb-0">
                                            <c:forEach items="${categories}" var="cate">
                                                • <a href="/search/category/${cate.name}">${cate.name}</a>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container -->

            <!-- Bootstrap core JavaScript -->
            <script src="<c:url value="/vendor/jquery/jquery2/jquery.min.js"/>"></script>
            <script src="<c:url value="/vendor/bootstrap/js/js2/bootstrap.bundle.min.js"/>"></script>
            <script>
                $(function () {
                    $("#datepicker").datepicker({dateFormat: 'yy-mm-dd'}).val();
                });
            </script>
        </div>
</body>

</html>
