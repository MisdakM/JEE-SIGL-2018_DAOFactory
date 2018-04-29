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
public class Client {
    
    String nom, prenom, adresse, tel, addrMail;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public Client(){
        
    }
    
    public Client(int id, String nom, String prenom, String addresse, String tel, String addrMail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = addresse;
        this.tel = tel;
        this.addrMail = addrMail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddrMail() {
        return addrMail;
    }

    public void setAddrMail(String addrMail) {
        this.addrMail = addrMail;
    }
}
