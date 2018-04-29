/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import FormHandlers.ClientFormHandler;
import Models.Client;
import DAO.*;
import Models.Commande;
import Models.Historique;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

/**
 *
 * @author Marouane
 */
@WebServlet(name = "CreationClient", urlPatterns = {"/creationClient"})
public class CreationClient extends HttpServlet {

    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM = "form";

    public static final String VUE_SUCCES = "/WEB-INF/affichageClient.jsp";
    public static final String VUE_FORM = "/WEB-INF/formulaireClient.jsp";

    public static String LOGIN_PAGE = "/WEB-INF/loginPage.jsp";

    HttpSession session;

    DAOFactory dao;
    ImplementationInterfaceClient daoClient;
    ImplementationInterfaceCommande daoCmd;
    ImplementationInterfaceHistorique daoHis;

    List<Client> allClients;

    @Override
    public void init() throws ServletException {
        dao = DAOFactory.getInstance();
        daoClient = (ImplementationInterfaceClient) dao.getClientDao();
        daoCmd = (ImplementationInterfaceCommande) dao.getCommandeDao();
        daoHis = (ImplementationInterfaceHistorique) dao.getHistoriqueDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        allClients = daoClient.getAllClients();
        request.setAttribute("allClients", allClients);

        if (session.getAttribute("login") != null && !session.getAttribute("login").equals("")) {
            request.setAttribute("user", session.getAttribute("login"));
            request.getRequestDispatcher(VUE_FORM).forward(request, response);
        } else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allClients = daoClient.getAllClients();
        request.setAttribute("allClients", allClients);

        if (request.getParameter("displayCmdsOfCl") != null) {
            // Ajax part
            Client cl = daoClient.getClient(Integer.valueOf(request.getParameter("displayCmdsOfCl").trim()));
            PrintWriter out = response.getWriter();
            List<Commande> clCmds = daoCmd.getAllCmdsOfThisClient(cl);
            
            if (clCmds.isEmpty()) {
                out.println("Ce client n'a pas encore des commandes");
            } else {
                out.println(getHtmlTableFromArrayOfCmds(clCmds));
            }
        } else if (request.getParameter("delete") != null) {
            // Supression client
            int idClient = Integer.parseInt(request.getParameter("idClient"));
            Client clicli = daoClient.getClient(idClient);
            if (daoCmd.getAllCmdsOfThisClient(clicli).isEmpty()) {
                daoClient.deleteClient(idClient);
                daoHis.addHistorique(new Historique(new Timestamp(System.currentTimeMillis()), "L'utilisateur " + session.getAttribute("login") + " a supprimmer le client Numero " + idClient));
                request.setAttribute("deleted", "Votre client a été supprimmer avec succés !");
                request.getRequestDispatcher(VUE_FORM).forward(request, response);
            } else {
                request.setAttribute("cannotDelete", "Vous ne pouvez pas supprimer un client qui a déjà des commande.");
//            response.sendRedirect("creationClient");
                request.getRequestDispatcher(VUE_FORM).forward(request, response);
            }
        } else if (request.getParameter("edit") != null) {
            // Modification client
            int idClient = Integer.parseInt(request.getParameter("idClient"));
            Client cli = daoClient.getClient(idClient);
            request.setAttribute("clientToEdit", cli);
            System.out.println("Edit tberkat whad lcilent howa lghadi ymchi ytedita >>" + cli.getNom());
//            response.sendRedirect("creationClient");
            request.getRequestDispatcher(VUE_FORM).forward(request, response);
        } else if (request.getParameter("save") != null) {
            // Enregistrement d'un nouveau client
            ClientFormHandler form = new ClientFormHandler();
            Client client = form.creerClient(request);

            request.setAttribute(ATT_CLIENT, client);
            request.setAttribute(ATT_FORM, form);

            if (form.getErreurs().isEmpty()) {
                ;
                daoHis.addHistorique(
                        new Historique(
                                new Timestamp(
                                        System.currentTimeMillis()),
                                "L'utilisateur " + session.getAttribute("login")
                                + " a ajouter le client Numero "
                                + daoClient.addClient(client)));

                request.setAttribute("success", "Votre client a été creer avec succés !");
//                response.sendRedirect("creationClient");
                this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
            } else {
                request.setAttribute("existErr", "Veuillez verifier tous les champs svp...");
                request.setAttribute("user", session.getAttribute("login"));
                this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
            }
        } else if (request.getParameter("editMode") != null) {
            // Mode de Modification d'un client
            ClientFormHandler form = new ClientFormHandler();
            Client client = form.creerClient(request);

            request.setAttribute(ATT_CLIENT, client);
            request.setAttribute(ATT_FORM, form);

            if (form.getErreurs().isEmpty()) {
                client.setId(Integer.parseInt(request.getParameter("idCli")));
                System.out.println(">>>>>>>>>>>>>>>>" + client.getId());
                daoClient.editClient(client);
                daoHis.addHistorique(new Historique(new Timestamp(System.currentTimeMillis()), "L'utilisateur " + session.getAttribute("login") + " a modifier le client numero " + client.getId()));
//                response.sendRedirect("creationClient");
                request.setAttribute("edited", "Votre client a été modifier avec succés !");
                this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
            } else {
                request.setAttribute("existErr", "Veuillez verifier tous les champs svp...");
                request.setAttribute("user", session.getAttribute("login"));
                int idClient = Integer.parseInt(request.getParameter("idClient"));
                Client cli = daoClient.getClient(idClient);
                request.setAttribute("clientToEdit", cli);
                this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
            }
        }
    }

    public String getHtmlTableFromArrayOfCmds(List<Commande> lstCmds) {
        String html = "<div class=\"table-responsive\">\n"
                + "    <table id=\"cmdTbl\" class=\"table table-hover\" style=\"font-size: 12px;\">\n"
                + "        <thead class=\"text-primary\">\n"
                + "            <tr>\n"
                + "                <th scope=\"col\">Client</th>\n"
                + "                <th scope=\"col\">Date</th>\n"
                + "                <th scope=\"col\">Montant</th>\n"
                + "                <th scope=\"col\">Mode de paiement</th>\n"
                + "                <th scope=\"col\">Statut de paiement</th>\n"
                + "                <th scope=\"col\">Mode de livraison</th>\n"
                + "                <th scope=\"col\">Statut de livraison</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        for (Commande c : lstCmds) {
            html += "                   <tr>\n"
                    + "                    <td hidden=\"hidden\">                            \n"
                    + "                        " + c.getIdCmd() + "\n"
                    + "                    </td>\n"
                    + "                    <td>" + c.getClient().getPrenom() + "</td>\n"
                    + "                    <td style=\"font-size: 9px;\">" + c.getDate() + "</td>\n"
                    + "                    <td class=\"text-right\">" + c.getMontant() + " Dhs</td>\n"
                    + "                    <td>" + c.getModePaiement() + "</td>\n"
                    + "                    <td>" + c.getStatutPaiement() + "</td>\n"
                    + "                    <td>" + c.getModeLivraison() + "</td>\n"
                    + "                    <td>" + c.getStatutLivraison() + "</td>\n"
                    + "                    <td>\n"
                    + "                        <form method=\"POST\" action=\"creationCommande\" >\n"
                    + "                            <!-- Modal -->\n"
                    + "                            <div class=\"modal fade\" id=\"" + c.getIdCmd() + "\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\n"
                    + "                                <div class=\"modal-dialog\" role=\"document\">\n"
                    + "                                    <div class=\"modal-content\">\n"
                    + "                                        <div class=\"modal-header\">\n"
                    + "                                            <h5 class=\"modal-title\" id=\"exampleModalLabel\">Confirmation</h5>\n"
                    + "                                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                    + "                                                <span aria-hidden=\"true\">&times;</span>\n"
                    + "                                            </button>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"modal-body\">\n"
                    + "                                            Vous alez etre rederiger vers le volet des commande apres cette operation.\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"modal-footer\">\n"
                    + "                                            <button type=\"button\" class=\"btn btn-secondary btn-round\" data-dismiss=\"modal\">Close</button>\n"
                    + "                                            <input type=\"submit\" id=\"delete\" name=\"delete\" class=\"btn btn-danger btn-round\" value=\"Supprimmer\"/>\n"
                    + "                                            <input type=\"submit\" id=\"edit\" name=\"edit\" class=\"btn btn-warning btn-round\" value=\"Modifier\"/>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                            <input type=\"hidden\" id=\"idCmd\" name=\"idCmd\" value=\"" + c.getIdCmd() + "\"/>\n"
                    + "                            <!-- Button trigger modal -->\n"
                    + "                            <button id=\"modalBtn\" data-toggle=\"modal\" data-target=\"#" + c.getIdCmd() + "\" class=\"btn btn-info btn-icon btn-round\" type=\"button\">\n"
                    + "                                <i class=\"now-ui-icons ui-1_settings-gear-63\"></i>\n"
                    + "                            </button>\n"
                    + "                            <!--<input type=\"submit\" id=\"edit\" name=\"edit\" class=\"btn btn-info\" value=\"Modifier\"/>-->\n"
                    + "                        </form>\n"
                    + "                    </td>\n"
                    + "                </tr>\n";
        }

        html += "        </tbody>\n"
                + "    </table>\n"
                + "</div>";
        return html;
    }

}
