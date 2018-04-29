/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Client;
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
public class ImplementationInterfaceClient implements InterfaceClient {

    private DAOFactory dao;
    private Connection con;

    public ImplementationInterfaceClient(DAOFactory daoClient) {
        this.dao = daoClient;
    }

    @Override
    public int addClient(Client cl) {
        con = dao.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement(
                    "INSERT INTO Client(nom, prenom, adresse, tel, email) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, cl.getNom());
            ps.setString(2, cl.getPrenom());
            ps.setString(3, cl.getAdresse());
            ps.setString(4, cl.getTel());
            ps.setString(5, cl.getAddrMail());

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
    public void deleteClient(int idCl) {
        con = dao.getConnection();
        
        PreparedStatement ps = null;
        
        try {
            ps = con.prepareStatement("DELETE FROM Client WHERE id=?");

            ps.setInt(1, idCl);

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
    public void editClient(Client cl) {
        con = dao.getConnection();
        
        PreparedStatement ps = null;
        
        try {
            String sql = "UPDATE Client SET nom = '" + cl.getNom()
                    + "',prenom='" + cl.getPrenom()
                    + "',adresse='" + cl.getAdresse()
                    + "',tel='" + cl.getTel()
                    + "',email='" + cl.getAddrMail()
                    + "' WHERE id=" + cl.getId();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + sql);
            ps = con.prepareStatement(sql);

            ps.execute();

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
    public Client getClient(int id) {
        Client cl = new Client();
        con = dao.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement("SELECT * FROM Client WHERE id=?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                cl.setId(rs.getInt("id"));
                cl.setNom(rs.getString("nom"));
                cl.setPrenom(rs.getString("prenom"));
                cl.setAdresse(rs.getString("adresse"));
                cl.setTel(rs.getString("tel"));
                cl.setAddrMail(rs.getString("email"));
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
        return cl;
    }

    @Override
    public List<Client> getAllClients() {
        con = dao.getConnection();
        List<Client> list = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement("SELECT * FROM Client");

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("tel"),
                        rs.getString("email")));
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
