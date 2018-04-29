<%-- 
    Document   : index
    Created on : Mar 12, 2018, 9:05:21 AM
    Author     : Marouane
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="assets/css/login.css" rel="stylesheet"/>
    </head>
    <body>

        <div class="container">
            <div class="card card-container">
                <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
                <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
                <p id="profile-name" class="profile-name-card"></p>
                <form action="auth" method="POST" class="form-signin">
                    <c:if test="${ !empty err }" >
                        <div class="form-group row">
                            <label for="nom" class="col-xs-12 alert alert-warning">
                                Note : <c:out value="${ err }"/>
                            </label>
                        </div>
                    </c:if>
                    <span id="reauth-email" class="reauth-email"></span>
                    <c:if test="${ !empty errList['errLogin']}">
                        <span>
                            ${errList['errLogin']}
                        </span>
                    </c:if>
                    <input type="login" id="login" name="login" class="form-control" placeholder="Email address" autofocus>
                    <c:if test="${ !empty errList['errPass']}">
                        <span>
                            ${errList['errPass']}
                        </span>
                    </c:if>
                    <input type="password" id="pass" name="pass" class="form-control" placeholder="Password">
                    <div id="remember" class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me" id="souv" name="souv"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
                </form><!-- /form -->
            </div><!-- /card-container -->
        </div><!-- /container -->
    </body>
</html>
