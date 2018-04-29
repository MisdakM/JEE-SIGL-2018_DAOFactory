/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormHandlers;

import DAO.DAOFactory;
import DAO.ImplementationInterfaceClient;
import Models.Client;
import Models.Commande;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Marouane
 */
public class CommandFormHandler {

    private static final String CHAMP_DATE = "dateCommande";
    private static final String CHAMP_MONTANT = "montantCommande";
    private static final String CHAMP_MODE_PAIEMENT = "modePaiementCommande";
    private static final String CHAMP_STATUT_PAIEMENT = "statutPaiementCommande";
    private static final String CHAMP_MODE_LIVRAISON = "modeLivraisonCommande";
    private static final String CHAMP_STATUT_LIVRAISON = "statutLivraisonCommande";

    private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    DAOFactory dao;

    ImplementationInterfaceClient daoClient;

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Commande creerCommande(HttpServletRequest request) {

        DateTime dt = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_DATE);
        String date = dt.toString(formatter);

        int idCl = Integer.parseInt(getValeurChamp(request, "idCl"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + idCl);

        dao = DAOFactory.getInstance();

        daoClient = (ImplementationInterfaceClient) dao.getClientDao();
        Client client = daoClient.getClient(idCl);
        System.out.println(">>>>>>>>>>>>" + client.getAddrMail() + " - " + client.getAdresse());

        String montant = getValeurChamp(request, CHAMP_MONTANT);
        String maDate = getValeurChamp(request, "dateCommande");
        System.out.println(">>>>>>>>>>>>>" + maDate);

        String modePaiement = getValeurChamp(request, CHAMP_MODE_PAIEMENT);
        String statutPaiement = getValeurChamp(request, CHAMP_STATUT_PAIEMENT);
        String modeLivraison = getValeurChamp(request, CHAMP_MODE_LIVRAISON);
        String statutLivraison = getValeurChamp(request, CHAMP_STATUT_LIVRAISON);

        Commande commande = new Commande();

        commande.setClient(client);

        commande.setDate(maDate);

        double valeurMontant = -1;
        try {
            valeurMontant = validationMontant(montant);
        } catch (Exception e) {
            setErreur(CHAMP_MONTANT, e.getMessage());
        }
        commande.setMontant(valeurMontant);

        try {
            maDate = validationDate(maDate);
        } catch (Exception e) {
            setErreur("dateCommande", e.getMessage());
        }
        commande.setDate(maDate);

        try {
            validationModePaiement(modePaiement);
        } catch (Exception e) {
            setErreur(CHAMP_MODE_PAIEMENT, e.getMessage());
        }
        commande.setModePaiement(modePaiement);

        try {
            validationStatutPaiement(statutPaiement);
        } catch (Exception e) {
            setErreur(CHAMP_STATUT_PAIEMENT, e.getMessage());
        }
        commande.setStatutPaiement(statutPaiement);

        try {
            validationModeLivraison(modeLivraison);
        } catch (Exception e) {
            setErreur(CHAMP_MODE_LIVRAISON, e.getMessage());
        }
        commande.setModeLivraison(modeLivraison);

        try {
            validationStatutLivraison(statutLivraison);
        } catch (Exception e) {
            setErreur(CHAMP_STATUT_LIVRAISON, e.getMessage());
        }
        commande.setStatutLivraison(statutLivraison);

        if (erreurs.isEmpty()) {
            resultat = "Succès de la création de la commande.";
        } else {
            resultat = "Échec de la création de la commande.";
        }
        return commande;
    }

    private double validationMontant(String montant) throws Exception {
        double temp;
        if (montant != null) {
            try {
                temp = Double.parseDouble(montant);
                if (temp < 0) {
                    throw new Exception("Le montant doit être un nombre positif.");
                }
            } catch (NumberFormatException e) {
                temp = -1;
                throw new Exception("Le montant doit être un nombre.");
            }
        } else {
            temp = -1;
            throw new Exception("Merci d'entrer un montant.");
        }
        return temp;
    }

    private void validationModePaiement(String modePaiement) throws Exception {
        if (modePaiement != null) {
            if (modePaiement.length() < 2) {
                throw new Exception("Le mode de paiement doit contenir au moins 2 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer un mode de paiement.");
        }
    }

    private String validationDate(String maDate) throws Exception {
        final String OLD_FORMAT = "yyyy-MM-dd hh:mm";
        final String NEW_FORMAT = "dd/MM/yyyy hh:mm:00";

        String oldDateString = maDate.replace("T", " ");
        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = null;
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException ex) {
            Logger.getLogger(CommandFormHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (d == null) {
            throw new Exception("Merci d'entrer une date de commande.");
        }

        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);

        System.out.println(" This is the new one >>>>>>>>>> " + newDateString);

        return newDateString;
    }

    private void validationStatutPaiement(String statutPaiement) throws Exception {
        if (statutPaiement != null) {
            if (statutPaiement.length() < 2) {
                throw new Exception("Le statut de paiement doit contenir au moins 2 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer un Statut Paiement.");
        }
    }

    private void validationModeLivraison(String modeLivraison) throws Exception {
        if (modeLivraison != null) {
            if (modeLivraison.length() < 2) {
                throw new Exception("Le mode de livraison doit contenir au moins 2 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer un mode de livraison.");
        }
    }

    private void validationStatutLivraison(String statutLivraison) throws Exception {
        if (statutLivraison != null) {
            if (statutLivraison.length() < 2) {
                throw new Exception("Le statut de livraison doit contenir au moins 2 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer un statut de livraison.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }
}
