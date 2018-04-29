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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import org.joda.time.LocalDateTime;

/**
 *
 * @author Marouane
 */
@WebServlet(name = "PageAccueil", urlPatterns = {"/pageAccueil"})
public class PageAccueil extends HttpServlet {

    public static final String ATT_CLIENT = "client";
    public static final String ATT_FORM = "form";

    public static final String VUE_SUCCES = "/WEB-INF/affichageClient.jsp";
    public static final String VUE_ACCUEIL = "/WEB-INF/pageAccueil.jsp";

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

        // La courbe des commande par année 
        ArrayList<Integer> cmdsOfTheYearTable = new ArrayList<>();
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("01").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("02").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("03").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("04").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("05").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("06").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("07").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("08").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("09").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("10").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("11").size());
        cmdsOfTheYearTable.add(daoCmd.getAllCmdsOfThisMonth("12").size());

        request.setAttribute("table", cmdsOfTheYearTable);

        List<Commande> cmdsOfLast24h = daoCmd.getAllCmdsOfLast24h();
        System.out.println("last 24h cmds Size" + cmdsOfLast24h.size());

        // Bnessba lsa3a lflan flaniya hach7al dles cmd
        LinkedHashMap<Integer, Integer> leData = new LinkedHashMap<>();

        System.out.println("Current hour >>> " + LocalDateTime.now());
        for (int h = 1; h <= 24; h++) {
            int nbrCmds = getCmdsNbrOfThisPeriod(cmdsOfLast24h, h, h - 1);
            int theHour = LocalDateTime.now().minusHours(h - 1).getHourOfDay();
            leData.put(theHour, nbrCmds);
            System.out.println("Before >> " + h + " and " + (h - 1) + "(" + theHour + "-" + (theHour + 1) + ") NbrCmds >> " + nbrCmds);
        }

        ArrayList<Integer> theHours = new ArrayList<>();
        ArrayList<Integer> theCmdNbrs = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : leData.entrySet()) {
            int theHour = entry.getKey();
            int nbrCmds = entry.getValue();
            theHours.add(theHour);
            theCmdNbrs.add(nbrCmds);
            System.out.println("(" + theHour + "-" + (theHour + 1) + ") NbrCmds >> " + nbrCmds);
        }
        request.setAttribute("hours", theHours);
        request.setAttribute("nbrCmds", theCmdNbrs);

        // La courbe des produits livré
        ArrayList<Integer> shippedCmds = new ArrayList<>();
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("01").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("02").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("03").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("04").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("05").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("06").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("07").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("08").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("09").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("10").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("11").size());
        shippedCmds.add(daoCmd.getShippedCmdsOfThisMonth("12").size());

        request.setAttribute("shippedCmds", shippedCmds);

        if (session.getAttribute("login") != null) {
            request.setAttribute("user", session.getAttribute("login"));
            request.getRequestDispatcher(VUE_ACCUEIL).forward(request, response);
        } else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("allCmds") != null) {
            // Ajax part
            PrintWriter out = response.getWriter();
            List<Commande> cmds = daoCmd.getAllCmds();
            if (cmds.isEmpty()) {
                out.println("Il n'y a pas encore des commandes");
            } else {
                out.println(getMinimizedHtmlTableFromArrayOfCmds(cmds));
            }
        }

        if (request.getParameter("history") != null) {
            PrintWriter out = response.getWriter();
            List<Historique> hist = daoHis.getAll();
            if (hist.isEmpty()) {
                out.println("Il n'y a pas encore d'historique");
            } else {
                out.println(historyPlease(hist));
            }
        }

        if (request.getParameter("deleteH") != null) {
            PrintWriter out = response.getWriter();
            daoHis.deleteHistorique(Integer.valueOf(request.getParameter("deleteH")));
            out.println("Done !");
        }
    }

    public boolean checkThisDateIfItsFromThisPeriod(Date dt, int startHour, int endHour) {
        Date startDate = LocalDateTime.now().minusHours(startHour).toDate();
        Date endDate = LocalDateTime.now().minusHours(endHour).toDate();
        System.out.println("-----------------");
        System.out.println(startDate);
        System.out.println(dt);
        System.out.println(endDate);
        System.out.println("-----------------");
        return dt.after(startDate) && dt.before(endDate);
    }

    public int getCmdsNbrOfThisPeriod(List<Commande> lst, int h1, int h2) {
        int count = 0;

        for (Commande c : lst) {
            try {
                SimpleDateFormat dbDateParser = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date dbDate = dbDateParser.parse(c.getDate());
                if (checkThisDateIfItsFromThisPeriod(dbDate, h1, h2)) {
                    count++;

                }
            } catch (ParseException ex) {
                Logger.getLogger(PageAccueil.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return count;
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
                    + "                                            Voulez vous faire ?\n"
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

    public String getMinimizedHtmlTableFromArrayOfCmds(List<Commande> lstCmds) {
        String html = "<div class=\"table-responsive\">\n"
                + "    <table id=\"cmdTbl\" class=\"table table-hover\" style=\"font-size: 12px;\">\n"
                + "        <thead class=\"text-primary\">\n"
                + "            <tr>\n"
                + "                <th scope=\"col\">Client</th>\n"
                + "                <th scope=\"col\">Date</th>\n"
                + "                <th scope=\"col\">Montant</th>\n"
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
                    + "                    <td>\n"
                    + "                    </td>\n"
                    + "                </tr>\n";
        }

        html += "        </tbody>\n"
                + "    </table>\n"
                + "</div>";
        return html;
    }

    public String historyPlease(List<Historique> hist) {
        String html = "<div class=\"table-responsive\">\n"
                + "    <table class=\"table table-hover\" style=\"font-size: 12px;\">\n"
                + "        <thead class=\"text-primary\">\n"
                + "            <tr>\n"
                + "                <th scope=\"col\">Date</th>\n"
                + "                <th scope=\"col\">Operation</th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>\n";
        for (Historique h : hist) {
            html += "                   <tr>\n"
                    + "                    <td>" + h.getDt() + "</td>\n"
                    + "                    <td>" + h.getPhrase() + "</td>\n"
                    + "                    <td>"
                    + "                       <button onclick=\"deleteHistory(this)\" id=\"" + h.getId() + "\" type=\"button\" rel=\"tooltip\" class=\"btn btn-danger btn-round btn-icon btn-icon-mini\">\n"
                    + "                          <i id='h' class=\"now-ui-icons ui-1_simple-remove\"></i>\n"
                    + "                       </button>\n"
                    + "                    </td>\n"
                    + "                </tr>\n";
        }

        html += "        </tbody>\n"
                + "    </table>\n"
                + "</div>";
        return html;
    }
}
