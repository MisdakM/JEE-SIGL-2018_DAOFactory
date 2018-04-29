<%-- 
    Document   : formulaireClient
    Created on : Mar 4, 2018, 10:41:59 PM
    Author     : Marouane
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Models.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container col-md-6 col-md-offset-3">
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><h4>Informations sur le client</h4></div>
                        <div class="panel-body">
                            <form method="GET">
                                <div class="form-group row">
                                    <label for="nom" class="col-xs-12 alert alert-warning">
                                        Note : <c:out value="${ form.resultat }"/>
                                    </label>
                                </div>
                                <div class="form-group row">
                                    <label for="nom" class="col-sm-3 col-form-label">Nom : </label>
                                    <div class="col-sm-9">
                                        <label class="form-control" id="nom" name="nom">
                                            <c:out value="${ client.nom }"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="prenom" class="col-sm-3 col-form-label">Prenom : </label>
                                    <div class="col-sm-9">
                                        <label class="form-control" id="prenom" name="prenom">
                                            <c:out value="${ client.prenom }"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="adresse" class="col-sm-3 col-form-label">Adresse : </label>
                                    <div class="col-sm-9">
                                        <label class="form-control" id="adresse" name="adresse" >
                                            <c:out value="${ client.adresse }"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="tel" class="col-sm-3 col-form-label">Téléphone :</label>
                                    <div class="col-sm-9">
                                        <label class="form-control" id="tel" name="tel" >
                                            <c:out value="${ client.tel }"/>
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mail" class="col-sm-3 col-form-label">Adresse mail : </label>
                                    <div class="col-sm-9">
                                        <label class="form-control" id="mail" name="mail">
                                            <c:out value="${ client.addrMail }" />
                                        </label>
                                    </div>
                                </div><hr>
                                <input type="button" onclick="window.location.href = '<c:url value="/creationClient"/>'" value="Retourner" class="btn btn-default"/>
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
