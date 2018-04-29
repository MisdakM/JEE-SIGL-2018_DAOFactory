/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Client;
import Models.Commande;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Marouane
 */
public interface InterfaceCommande {
    public int addCmd(Commande cmd);
    public void deleteCmd(int idCmd);
    public void editCmd(Commande cmd);
    public Commande getCmd(int id);
    public List<Commande> getAllCmds();
    public List<Commande> getAllCmdsOfThisMonth(String month);
    public List<Commande> getAllCmdsOfThisClient(Client cl);
    public List<Commande> getAllCmdsOfLast24h();
    public List<Commande> getShippedCmdsOfThisMonth(String month);
}
