/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormHandlers;

import Models.Client;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Marouane
 */
public class ClientFormHandler {

    private static final String CHAMP_NOM = "nomClient";
    private static final String CHAMP_PRENOM = "prenomClient";
    private static final String CHAMP_ADRESSE = "adresseClient";
    private static final String CHAMP_TELEPHONE = "telephoneClient";
    private static final String CHAMP_EMAIL = "emailClient";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }
    
    // passi request nredlik client en utilisants les champ lijayen m3a had request
    public Client creerClient(HttpServletRequest request) {
        String nom = getValeurChamp(request, CHAMP_NOM);
        String prenom = getValeurChamp(request, CHAMP_PRENOM);
        String adresse = getValeurChamp(request, CHAMP_ADRESSE);
        String telephone = getValeurChamp(request, CHAMP_TELEPHONE);
        String email = getValeurChamp(request, CHAMP_EMAIL);

        Client client = new Client();
        
        // Hna kanverifiéw chaque champ avec try{...}
        // ila l9ina chi erreur y3ni la fonction validationBlabla(...) dartlina throw Exception
        // alors kandirolha catch, wkaninistialiséw la variable erreurs <champ, msgErr>
        // champ : howa le nom du champ | msgErr : howa likayen fach kal9aw err wkandiro throw new Exception("blabla...");
        try {
            validationNom(nom);
        } catch (Exception e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }
        client.setNom(nom);

        try {
            validationPrenom(prenom);
        } catch (Exception e) {
            setErreur(CHAMP_PRENOM, e.getMessage());
        }
        client.setPrenom(prenom);

        try {
            validationAdresse(adresse);
        } catch (Exception e) {
            setErreur(CHAMP_ADRESSE, e.getMessage());
        }
        client.setAdresse(adresse);

        try {
            validationTelephone(telephone);
        } catch (Exception e) {
            setErreur(CHAMP_TELEPHONE, e.getMessage());
        }
        client.setTel(telephone);

        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        client.setAddrMail(email);
        
        // ila mal9ina hta chi erreur donc ghadi ndiro un msg de succés fresultat wla l3aks
        if (erreurs.isEmpty()) {
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

        return client;
    }

    // Hado les methodes de validation de chaque champ
    private void validationNom(String nom) throws Exception {
        if (nom != null) {
            if (nom.length() < 2) {
                throw new Exception("Le nom d'utilisateur doit contenir au moins 2 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer un nom d'utilisateur.");
        }
    }

    private void validationPrenom(String prenom) throws Exception {
        if (prenom != null) {
            if (prenom.length() < 2) {
                throw new Exception("Le prénom d'utilisateur doit contenir au moins 2 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer un prénom d'utilisateur.");
        }
    }

    private void validationAdresse(String adresse) throws Exception {
        if (adresse != null) {
            if (adresse.length() < 10) {
                throw new Exception("L'adresse de livraison doit contenir au moins 10 caractères.");
            }
        } else {
            throw new Exception("Merci d'entrer une adresse de livraison.");
        }
    }

    private void validationTelephone(String telephone) throws Exception {
        if (telephone != null) {
            if (!telephone.matches("^\\d+$")) {
                throw new Exception("Le numéro de téléphone doit uniquement contenir des chiffres.");
            } else if (telephone.length() < 4) {
                throw new Exception("Le numéro de téléphone doit contenir au moins 4 chiffres.");
            }
        } else {
            throw new Exception("Merci d'entrer un numéro de téléphone.");
        }
    }

    private void validationEmail(String email) throws Exception {
        if (email != null) {
            if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception("Merci de saisir une adresse mail valide.");
        }
        } else {
            throw new Exception("Merci d'entrer un email.");
        }
        
    }

    // Had la méthode kanpassilha (request, nomChamp) katredli chno kayn fhad champ
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }
}
