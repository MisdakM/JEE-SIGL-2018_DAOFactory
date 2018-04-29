/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marouane
 */
public class DBCon {

    private Connection con;

    public static String serverIP = "65.19.141.67";

    public DBCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isUserValid(User user) {
        boolean isValid = false;
        try {
            // Se connecter a la Base de Données
            con = DriverManager.getConnection("jdbc:mysql://" + serverIP + "/misdakm_validationjee", "misdakm_root", "rootroot");
            String sql = "SELECT * FROM user WHERE login='" + user.getLogin() + "' AND pass='" + user.getPass() + "';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            isValid = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isValid;
    }

    public void addCookieKey(int userId, String key) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + serverIP + "/misdakm_validationjee", "misdakm_root", "rootroot");
            String sql = "INSERT INTO cookieskeys VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, key);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserWhithId(User user) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + serverIP + "/misdakm_validationjee", "misdakm_root", "rootroot");
            String sql = "SELECT id FROM User WHERE login ='" + user.getLogin() + "' AND pass = '" + user.getPass() + "';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int getTheAssociatedUserId(String key) {
        int userid = 0;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + serverIP + "/misdakm_validationjee", "misdakm_root", "rootroot");
            String sql = "SELECT userid FROM cookieskeys WHERE cookiekey ='" + key + "';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                userid = rs.getInt("userid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userid;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + serverIP + "/misdakm_validationjee", "misdakm_root", "rootroot");
            String sql = "SELECT * FROM user WHERE id='" + id + "';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                user = new User(rs.getString("login"), rs.getString("pass"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void deleteAllTheSeesionsOfThisUser(int id) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + serverIP + "/misdakm_validationjee", "misdakm_root", "rootroot");
            String sql = "DELETE FROM cookieskeys WHERE userid = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
