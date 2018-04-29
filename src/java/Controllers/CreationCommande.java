package Controllers;

import static Controllers.Authentification.LOGIN_PAGE;
import static Controllers.CreationClient.ATT_CLIENT;
import static Controllers.CreationClient.ATT_FORM;
import static Controllers.CreationClient.VUE_FORM;
import DAO.*;
import FormHandlers.ClientFormHandler;
import FormHandlers.CommandFormHandler;
import Models.Client;
import Models.Commande;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;

@WebServlet(name = "CreationCommande", urlPatterns = {"/creationCommande"})
public class CreationCommande extends HttpServlet {

    public static final String ATT_COMMANDE = "commande";
    public static final String ATT_FORM = "form";

    public static final String VUE_SUCCES = "/WEB-INF/affichageCommande.jsp";
    public static final String VUE_FORM = "/WEB-INF/formulaireCommande.jsp";

    public static String LOGIN_PAGE = "/WEB-INF/loginPage.jsp";

    HttpSession session;

    DAOFactory dao;

    ImplementationInterfaceClient daoClient;
    ImplementationInterfaceCommande daoCmd;

    List<Client> allClients;
    List<Commande> allCmds;

    @Override
    public void init() throws ServletException {
        dao = DAOFactory.getInstance();
        daoClient = (ImplementationInterfaceClient) dao.getClientDao();
        daoCmd = (ImplementationInterfaceCommande) dao.getCommandeDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        allClients = daoClient.getAllClients();
        allCmds = daoCmd.getAllCmds();

        for (Client c : allClients) {
            System.out.println("Hahowa whd client >>>>>>> " + c.getNom());
        }

        request.setAttribute("allClients", allClients);
        request.setAttribute("allCmds", allCmds);

        if (session.getAttribute("login") != null && !session.getAttribute("login").equals("")) {
            request.setAttribute("user", session.getAttribute("login"));
            request.getRequestDispatcher(VUE_FORM).forward(request, response);
        } else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        allClients = daoClient.getAllClients();
        allCmds = daoCmd.getAllCmds();

        request.setAttribute("allClients", allClients);
        request.setAttribute("allCmds", allCmds);

        if (request.getParameter("shipped") != null) {
            Commande commande = daoCmd.getCmd(Integer.parseInt(request.getParameter("idCmd")));
            commande.setStatutLivraison("Shipped");
            
            daoCmd.editCmd(commande);

            request.setAttribute("edited", "Votre commande a été modifier avec succés !");
            request.getRequestDispatcher(VUE_FORM).forward(request, response);

        } else if (request.getParameter("delete") != null) {
            // Supression client
            int idCmd = Integer.parseInt(request.getParameter("idCmd"));

            daoCmd.deleteCmd(idCmd);
            request.setAttribute("deleted", "Votre commande a été supprimmer avec succés !");
            request.getRequestDispatcher(VUE_FORM).forward(request, response);

        } else if (request.getParameter("edit") != null) {
            // Modification Commande
            int idCmd = Integer.parseInt(request.getParameter("idCmd"));
            Commande cmd = daoCmd.getCmd(idCmd);
            request.setAttribute("cmdToEdit", cmd);
//            response.sendRedirect("creationClient");
            request.getRequestDispatcher(VUE_FORM).forward(request, response);
        } else if (request.getParameter("save") != null) {
            CommandFormHandler form = new CommandFormHandler();
            Commande commande = form.creerCommande(request);

            // Ajouter Commande
            System.out.println(">>>>>>>>>>>>>> Hana f save!=null <<<<<<<<<<");
            if (form.getErreurs().isEmpty()) {
                daoCmd.addCmd(commande);
                request.setAttribute("success", "Votre client a été creer avec succés !");
                this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
            } else {
                request.setAttribute(ATT_COMMANDE, commande);
                request.setAttribute(ATT_FORM, form);
                request.setAttribute("user", session.getAttribute("login"));
                request.setAttribute("existErr", "Veuillez verifier tous les champs svp...");
                this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
            }
        } else if (request.getParameter("editMode") != null) {
            CommandFormHandler form = new CommandFormHandler();
            Commande commande = form.creerCommande(request);

            // Mode de Modification d'une Commande
            request.setAttribute(ATT_CLIENT, commande);
            request.setAttribute(ATT_FORM, form);

            if (form.getErreurs().isEmpty()) {
                System.out.println("Before >>> id = " + commande.getIdCmd());
                commande.setIdCmd(Integer.parseInt(request.getParameter("idCmd")));
                System.out.println("Before >>> id = " + commande.getIdCmd());
                daoCmd.editCmd(commande);

//                response.sendRedirect("creationClient");
                request.setAttribute("edited", "Votre commande a été modifier avec succés !");
                this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
            } else {
                request.setAttribute("existErr", "Veuillez verifier tous les champs svp...");
                request.setAttribute("user", session.getAttribute("login"));

                int idCmd = Integer.parseInt(request.getParameter("idCmd"));
                Commande cmd = daoCmd.getCmd(idCmd);
                request.setAttribute("cmdToEdit", cmd);
                request.getRequestDispatcher(VUE_FORM).forward(request, response);
            }
        }

    }
}
