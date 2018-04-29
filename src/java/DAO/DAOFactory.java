/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marouane
 */
public class DAOFactory {

    public static String serverIP = "65.19.141.67";

    private String url;
    private String username;
    private String password;

    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DAOFactory("jdbc:mysql://" + serverIP + "/misdakm_validationjee", "misdakm_root", "rootroot");
    }

    Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Les interfaces
    public InterfaceClient getClientDao() {
        return new ImplementationInterfaceClient(this);
    }

    public InterfaceCommande getCommandeDao() {
        return new ImplementationInterfaceCommande(this);
    }

    public InterfaceHistorique getHistoriqueDao() {
        return new ImplementationInterfaceHistorique(this);
    }
}
