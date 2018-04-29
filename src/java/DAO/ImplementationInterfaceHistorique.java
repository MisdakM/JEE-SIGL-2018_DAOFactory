/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Client;
import Models.Historique;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marouane
 */
public class ImplementationInterfaceHistorique implements InterfaceHistorique {

    DAOFactory dao;
    Connection con;

    public ImplementationInterfaceHistorique(DAOFactory dao) {
        this.dao = dao;
    }

    @Override
    public int addHistorique(Historique his) {
        con = dao.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(
                    "INSERT INTO History(date, phrase) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, new java.sql.Date(his.getDt().getTime()));
            ps.setString(2, his.getPhrase());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                long id = rs.getLong(1);
                return (int) id;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImplementationInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            };
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            };
        }
        return -1;
    }

    @Override
    public void deleteHistorique(int id) {
        con = dao.getConnection();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM History WHERE id=?");

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ImplementationInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            };
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            };
        }
    }

    @Override
    public void viderHistorique() {
        con = dao.getConnection();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM History");
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ImplementationInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            };
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            };
        }
    }

    @Override
    public List<Historique> getAll() {
        con = dao.getConnection();
        List<Historique> list = new ArrayList<>();

        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try {
            ps = con.prepareStatement("SELECT * FROM History");

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Historique(
                        rs.getInt("id"),
                        rs.getDate("date"),
                        rs.getString("phrase")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImplementationInterfaceClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            };
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            };
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            };
        }

        return list;
    }

}
