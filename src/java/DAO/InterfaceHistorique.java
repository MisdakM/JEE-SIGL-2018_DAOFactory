/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Client;
import Models.Historique;
import java.util.List;

/**
 *
 * @author Marouane
 */
public interface InterfaceHistorique {
    public int addHistorique(Historique his);
    public void deleteHistorique(int id);
    public void viderHistorique();
    public List<Historique> getAll();
}
