/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Client;
import java.util.List;

/**
 *
 * @author Marouane
 */
public interface InterfaceClient {
    public int addClient(Client cl);
    public void deleteClient(int idCl);
    public void editClient(Client cl);
    public Client getClient(int id);
    public List<Client> getAllClients();
}
