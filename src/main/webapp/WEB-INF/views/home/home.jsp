<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Event GO !</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/theme/css/blog-home.css"/>" rel="stylesheet">

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
                        <h2 class="card-title">${eve.name}</h2>
                        <p class="card-text">${eve.description}</p>
                        <a href="/event/details/${eve.id}" class="btn btn-primary">Więcej &rarr;</a>
                    </div>
                    <div class="card-footer text-muted">
                        <strong><p class="card-title"><a>${eve.localization.city} ${eve.eventDate}</a></p>
                        </strong>
                        Posted on ${eve.createdOn} by ${eve.user.username}
                    </div>
                </div>
            </c:forEach>
        </div>
        <%--        <!-- Sidebar Widgets Column -->--%>
        <div class="col-md-4">
            <!-- Search Widget -->
            <form:form method="post" modelAttribute="event">
                <div class="card my-4">
                    <h5 class="card-header">Search by name..</h5>
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
                <h5 class="card-header">Category</h5>
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

</body>

</html>
