<%-- 
    Document   : formClient
    Created on : Mar 10, 2018, 3:13:39 PM
    Author     : Marouane
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="table-responsive">
    <table id="cmdTbl" class="table table-hover" style="font-size: 12px;">
        <thead class="text-primary">
            <tr>
                <th scope="col">Client</th>
                <th scope="col">Date</th>
                <th scope="col">Montant</th>
                <th scope="col">Mode de paiement</th>
                <th scope="col">Statut de paiement</th>
                <th scope="col">Mode de livraison</th>
                <th scope="col">Statut de livraison</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${allCmds}" var="cmd">
                <tr>
                    <td hidden="hidden">                            
                        ${cmd.idCmd}
                    </td>
                    <td>${cmd.client.prenom}</td>
                    <td style="font-size: 9px;">${cmd.date}</td>
                    <td class="text-right">${cmd.montant} Dhs</td>
                    <td>${cmd.modePaiement}</td>
                    <td>${cmd.statutPaiement}</td>
                    <td>${cmd.modeLivraison}</td>
                    <td>${cmd.statutLivraison}</td>
                    <td>
                        <form method="POST" action="creationCommande" >
                            <!-- Modal -->
                            <div class="modal fade" id="${cmd.idCmd}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Voulez vous faire Mr. ${user} ?
                                        </div>
                                        <div class="modal-footer" style="margin: 0 auto; float: none;">
                                            <button type="button" class="btn btn-secondary btn-round" data-dismiss="modal">Close</button>
                                            <input type="submit" id="delete" name="delete" class="btn btn-danger btn-round" value="Supprimmer"/>
                                            <input type="submit" id="edit" name="edit" class="btn btn-warning btn-round" value="Modifier"/>
                                            <input type="submit" id="shipped" name="shipped" class="btn btn-info btn-round" value="Confirmer la livraison"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" id="idCmd" name="idCmd" value="${cmd.idCmd}"/>
                            <!-- Button trigger modal -->
                            <button id="modalBtn" data-toggle="modal" data-target="#${cmd.idCmd}" class="btn btn-info btn-icon btn-round" type="button">
                                <i class="now-ui-icons ui-1_settings-gear-63"></i>
                            </button>
                            <!--<input type="submit" id="edit" name="edit" class="btn btn-info" value="Modifier"/>-->
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<hr>