<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">


<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center">Create Account</h4>
            <p class="text-center">Get started with your free account</p>
            <p>
<%--                <a href="" class="btn btn-block btn-twitter"> <i class="fab fa-twitter"></i>   Login via Twitter</a>--%>
<%--                <a href="" class="btn btn-block btn-twitter"> <i class="fab fa-twitter"></i>   Login via Twitter</a>--%>
<%--                <a href="" class="btn btn-block btn-facebook"> <i class="fab fa-facebook-f"></i>   Login via facebook</a>--%>
            </p>
<%--            <p class="divider-text">--%>
<%--                <span class="bg-light">OR</span>--%>
<%--            </p>--%>
            <form:form method="post" modelAttribute="user">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <form:input path="username" class="form-control" placeholder="Nazwa Użytkownika" type="text"/>
                    <form:errors path="username"/> ${message}
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <form:input path="firstName" class="form-control" placeholder="Imię" type="text"/>
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <form:input path="lastName" class="form-control" placeholder="Nazwisko" type="text"/>
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                    </div>
                    <form:input path="email" class="form-control" placeholder="Email adres" type="email" />
                </div> <!-- form-group// -->
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
                    </div>
                    <form:input path="telNumber" class="form-control" placeholder="Phone number" type="text"/>
                </div> <!-- form-group// -->

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <form:input path="password" class="form-control" placeholder="Create password" type="password"/>
                    <form:errors path="password"/>
                </div> <!-- form-group// -->
<%--                <div class="form-group input-group">--%>
<%--                    <div class="input-group-prepend">--%>
<%--                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>--%>
<%--                    </div>--%>
<%--                    <input class="form-control" placeholder="Repeat password" type="password">--%>
<%--                </div> <!-- form-group// -->--%>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Create Account </button>
                </div> <!-- form-group// -->
                <p class="text-center">Have an account? <a href="/login">Log In</a> </p>
            </form:form>
        </article>
    </div> <!-- card.// -->

</div>
<!--container end.//-->

