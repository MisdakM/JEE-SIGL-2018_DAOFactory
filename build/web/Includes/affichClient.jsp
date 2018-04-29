<%-- 
    Document   : formClient
    Created on : Mar 10, 2018, 3:13:39 PM
    Author     : Marouane
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="clTbl" class="table table-hover">
    <thead>
        <tr>
            <th scope="col">Nom</th>
            <th scope="col">Prenom</th>
            <th scope="col">Adresse</th>
            <th scope="col">Telephone</th>
            <th scope="col">Email</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${allClients}" var="client">
            <tr>
                <td hidden="hidden">                            
                    ${client.id}
                </td>
                <td>${client.nom}</td>
                <td>${client.prenom}</td>
                <td>${client.adresse}</td>
                <td>${client.tel}</td>
                <td>${client.addrMail}</td>
                <td>
                    <form method="POST" action="creationClient" >
                        <!-- Modal -->
                        <div class="modal fade" id="${client.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Voulez vous vraiment supprimer le client qui a l'id : ${client.nom} ?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <input type="submit" id="delete" name="delete" class="btn btn-danger" value="Confirmer"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="idClient" name="idClient" value="${client.id}"/>
                        <!-- Button trigger modal -->
                        <button id="deleteBtn" type="button" class="btn btn-danger btn-icon btn-round" data-target="#${client.id}">
                            <i class="now-ui-icons ui-1_simple-remove"></i>
                        </button>
                        <button type="button" class="displayCmds btn btn-secondary btn-icon btn-round" data-target="${client.id}">
                            <i class="now-ui-icons shopping_delivery-fast"></i>
                        </button>
                        <input type="submit" id="edit" name="edit" class="btn btn-info" value="Modifier"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<hr>