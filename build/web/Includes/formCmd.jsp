<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${!empty cmdToEdit}">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h5 class="title">Modifier la commande</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12 pr-1">
                                <label>
                                    Selectionner un client
                                </label>
                                <select required class="form-control" id="lstClient" name="lstClient">
                                    <option id="${cmdToEdit.client.id}" selected>${cmdToEdit.client.nom} ${cmdToEdit.client.prenom}</option>
                                </select>
                                <input id="idCl" value="${cmdToEdit.client.id}" name="idCl" hidden/>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-md-6 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['dateCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['dateCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Date *</label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="datetime-local" id="dateCommande" name="dateCommande" class="form-control" placeholder="Votre nom svp..." value="${cmdToEdit.date}">
                                </div>
                            </div>
                            <div class="col-md-6 pl-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['montantCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['montantCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Montant Commande </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="number" min="0" value="${cmdToEdit.montant}" class="form-control" id="montantCommande" name="montantCommande" placeholder="Ecrire votre montant..." >
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-7">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['modePaiementCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['modePaiementCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Mode </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <select type="text" id="modePaiementCommande" name="modePaiementCommande" value="${cmdToEdit.modePaiement}" placeholder="Ecrire votre Mode de paiement..." class="form-control" >
                                        <option>Chéque</option>
                                        <option>Carte Crédit</option>
                                        <option>Espéce</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['statutPaiementCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['statutPaiementCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Statut de paiement </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" class="form-control" id="statutPaiementCommande" name="statutPaiementCommande" value="${cmdToEdit.statutPaiement}" placeholder="Le statut de paiement...">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['modeLivraisonCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['modeLivraisonCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Mode de livraison </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="${cmdToEdit.modeLivraison}" placeholder="Saisissez le mode de livraison..." class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['statutLivraisonCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['statutLivraisonCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Statut de livraison </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="${cmdToEdit.statutLivraison}" placeholder="Saisissez le statut de livraison..." class="form-control">
                                </div>
                            </div>
                        </div>
                        <hr>
                        <input type="text" name="idCmd" name="idCmd" value="${cmdToEdit.idCmd}" hidden/>
                        <input type="submit" id="editMode" name="editMode" value="Enregistrer les modifications" class="btn btn-info btn-round btn-lg btn-block"/>
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
                        <h5 class="title">Nouvelle commande</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12 pr-1">
                                <label  >
                                    Selectionner un client
                                </label>
                                <select required class="form-control" onchange="showOptions(this)" id="lstClient" name="lstClient">
                                    <option disabled selected value> -- Selectionner un client -- </option>
                                    <c:choose>
                                        <c:when test="${!empty allClients}">
                                            <c:forEach items="${allClients}" var="client">
                                                <option id="${client.id}">${client.nom} ${client.prenom}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option> Il n'y aucun client </option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <input id="idCl" name="idCl" hidden/>
                            </div>
                        </div> <hr>
                        <div class="row">
                            <div class="col-md-6 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['dateCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['dateCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Date *</label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="datetime-local" id="dateCommande" name="dateCommande" class="form-control" placeholder="Votre nom svp...">
                                </div>
                            </div>
                            <div class="col-md-6 pl-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['montantCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['montantCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Montant Commande </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="number" min="0" class="form-control" id="montantCommande" name="montantCommande" placeholder="Ecrire votre montant..." >
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-7">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['modePaiementCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['modePaiementCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Mode </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <select type="text" id="modePaiementCommande" name="modePaiementCommande" placeholder="Ecrire votre Mode de paiement..." class="form-control" >
                                        <option>Chéque</option>
                                        <option>Carte Crédit</option>
                                        <option>Espéce</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['statutPaiementCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['statutPaiementCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Statut de paiement </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" class="form-control" id="statutPaiementCommande" name="statutPaiementCommande" placeholder="Le statut de paiement...">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['modeLivraisonCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['modeLivraisonCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Mode de livraison </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" placeholder="Saisissez le mode de livraison..." class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 pr-1">
                                <div class="form-group">
                                    <c:choose >
                                        <c:when test="${!empty form.erreurs['statutLivraisonCommande']}">
                                            <label class="text-warning">
                                                ${form.erreurs['statutLivraisonCommande']}
                                            </label>
                                        </c:when>
                                        <c:otherwise>
                                            <label>Statut de livraison </label>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" placeholder="Saisissez le statut de livraison..." class="form-control">
                                </div>
                            </div>
                        </div>
                        <hr>
                        <input type="submit" id="save" name="save" value="Enregistrer la commande" class="btn btn-info btn-round btn-lg btn-block"/>
                    </div>
                </div>
            </div>   
        </div>
    </c:otherwise>
</c:choose>