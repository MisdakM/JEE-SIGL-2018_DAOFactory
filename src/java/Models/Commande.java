/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Marouane
 */

public class Commande {
    /* Propriétés du bean */
    private int idCmd;
    private Client client;
    private String date;
    private Double montant;
    private String modePaiement;
    private String statutPaiement;
    private String modeLivraison;
    private String statutLivraison;

    public Commande(int idCmd, Client client, String date, Double montant, String modePaiement, String statutPaiement, String modeLivraison, String statutLivraison) {
        this.idCmd = idCmd;
        this.client = client;
        this.date = date;
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.statutPaiement = statutPaiement;
        this.modeLivraison = modeLivraison;
        this.statutLivraison = statutLivraison;
    }
    
    public Commande(int idCmd, int idCl, String date, Double montant, String modePaiement, String statutPaiement, String modeLivraison, String statutLivraison) {
        this.idCmd = idCmd;
        this.client = new Client();
        this.client.setId(idCl);
        this.date = date;
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.statutPaiement = statutPaiement;
        this.modeLivraison = modeLivraison;
        this.statutLivraison = statutLivraison;
    }

    public Commande() {
    }
    
    public int getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(int idCmd) {
        this.idCmd = idCmd;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient( Client client ) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant( Double montant ) {
        this.montant = montant;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement( String modePaiement ) {
        this.modePaiement = modePaiement;
    }

    public String getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement( String statutPaiement ) {
        this.statutPaiement = statutPaiement;
    }

    public String getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison( String modeLivraison ) {
        this.modeLivraison = modeLivraison;
    }

    public String getStatutLivraison() {
        return statutLivraison;
    }

    public void setStatutLivraison( String statutLivraison ) {
        this.statutLivraison = statutLivraison;
    }
}