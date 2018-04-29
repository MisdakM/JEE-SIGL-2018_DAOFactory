/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Marouane
 */
public class Historique {
    int id;
    String phrase;
    Date dt;
    
    public Historique(Date dt, String phrase) {
        this.phrase = phrase;
        this.dt = dt;
    }
    
    public Historique(int id, Date dt, String phrase) {
        this.id = id;
        this.phrase = phrase;
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }
    
    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
    
}
