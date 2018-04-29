<%-- 
    Document   : formClient
    Created on : Mar 10, 2018, 3:13:39 PM
    Author     : Marouane
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${!empty clientToEdit}">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h5 class="title">Nouveu Client</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['nomClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['nomClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Nom </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="nomClient" name="nomClient" value="${clientToEdit.nom}" class="form-control" placeholder="Votre nom svp...">
                                </div>
                            </div>
                            <div class="col-md-6 pl-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['prenomClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['prenomClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Prenom </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="prenomClient" name="prenomClient" value="${clientToEdit.prenom}" class="form-control" placeholder="Votre prenom svp...">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-7">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['adresseClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['adresseClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Addresse </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="adresseClient" name="adresseClient" value="${clientToEdit.adresse}" class="form-control" placeholder="Votre adresse svp...">
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['telephoneClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['telephoneClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Telephone </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" class="form-control" id="telephoneClient"  value="${clientToEdit.tel}" name="telephoneClient" placeholder="Votre téléphone svp...">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['emailClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['emailClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Adress mail </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="emailClient" name="emailClient" value="${clientToEdit.addrMail}" placeholder="Ecrire votre adresse mail..." class="form-control">
                                </div>
                            </div>
                        </div>
                        <hr>
                        <input type="hidden" id="idCli" name="idCli" value="${clientToEdit.id}"/>
                        <input type="submit" id="editMode" name="editMode" 
                               onclick="confirmAction(event, 'Voulez vous vraiment enregistrer les modification de ce client ?')" 
                               value="Enregistrer les modification du client"
                               class="btn btn-warning btn-round btn-lg btn-block"/>
                    </div>
                </div>
            </div>   
        </div>
    </c:when>
    <c:otherwise>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h5 class="title">Nouveu Client</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['nomClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['nomClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Nom </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="nomClient" name="nomClient" class="form-control" placeholder="Votre nom svp...">
                                </div>
                            </div>
                            <div class="col-md-6 pl-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['prenomClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['prenomClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Prenom </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="prenomClient" name="prenomClient" class="form-control" placeholder="Votre prenom svp...">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-7">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['adresseClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['adresseClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Addresse </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="adresseClient" name="adresseClient" class="form-control" placeholder="Votre adresse svp...">
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['telephoneClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['telephoneClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Telephone </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" class="form-control" id="telephoneClient" name="telephoneClient" placeholder="Votre téléphone svp...">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['emailClient']}">
                                            <label class="text-warning">
                                                ${form.erreurs['emailClient']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Adresse mail </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="emailClient" name="emailClient" placeholder="Ecrire votre adresse mail..." class="form-control">
                                </div>
                            </div>
                        </div>
                        <hr>
                        <input type="submit" id="save" name="save" value="Enregistrer le client" class="btn btn-info btn-round btn-lg btn-block"/>
                    </div>
                </div>
            </div>   
        </div>
    </c:otherwise>
</c:choose>
