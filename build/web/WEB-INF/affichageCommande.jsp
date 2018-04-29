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
                            <div class="form-group row">
                                <label for="nom" class="col-xs-12 alert alert-warning">
                                    Note : <c:out value="${ form.resultat }"/>
                                </label>
                            </div>

                            <div class="form-group row">
                                <label for="nom" class="col-sm-3 col-form-label">Nom : </label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="nom" name="nom">
                                        <c:out value="${ commande.client.nom }"/>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="prenom" class="col-sm-3 col-form-label">Prenom : </label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="prenom" name="prenom">
                                        <c:out value="${ commande.client.prenom }"/>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="adresse" class="col-sm-3 col-form-label">Adresse : </label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="adresse" name="adresse" >
                                        <c:out value="${ commande.client.adresse }"/>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="tel" class="col-sm-3 col-form-label">Téléphone :</label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="tel" name="tel" >
                                        <c:out value="${ commande.client.tel }"/>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="mail" class="col-sm-3 col-form-label">Adresse mail : </label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="mail" name="mail">
                                        <c:out value="${ commande.client.addrMail }"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-group">
                <div class="panel panel-default">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><h4>Informations sur la commande</h4></div>
                        <div class="panel-body">
                            <div class="form-group row">
                                <label for="date" class="col-sm-3 col-form-label">Date : </label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="date" name="date">
                                        <c:out value="${ commande.date }" />
                                    </label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="montant" class="col-sm-3 col-form-label">Montant : </label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="montant" name="montant">
                                        <c:out value="${ commande.montant }"/>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="modePaie" class="col-sm-3 col-form-label">Mode paiement : </label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="modePaie" name="modePaie" >
                                        <c:out value="${ commande.modePaiement }" />
                                    </label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="statPaie" class="col-sm-3 col-form-label">Statut paiement : </label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="statPaie" name="statPaie" >
                                        <c:out value="${ commande.statutPaiement }"/>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="modeLivr" class="col-sm-3 col-form-label">Mode livraison :</label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="modeLivr" name="modeLivr" >
                                        <c:out value="${ commande.modeLivraison }"/>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="statuLivr" class="col-sm-3 col-form-label">Status livraison :</label>
                                <div class="col-sm-9">
                                    <label class="form-control" id="statuLivr" name="statuLivr" >
                                        <c:out value="${ commande.statutLivraison }"/>
                                    </label>
                                </div>
                            </div>
                            <hr>
                            <input type="button" onclick="window.location.href = '<c:url value="/creationCommande"/>'" value="Retourner" class="btn btn-default"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
