/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Client;
import Models.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.LocalDateTime;

/**
 *
 * @author Marouane
 */
public class ImplementationInterfaceCommande implements InterfaceCommande {

    private DAOFactory dao;
    private Connection con;
    private ImplementationInterfaceClient clientDao;

    public ImplementationInterfaceCommande(DAOFactory dao) {
        this.dao = dao;
    }

    @Override
    public int addCmd(Commande cmd) {
        con = dao.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO Commande(idClient, date, montant, modePaiement, statusPaiement, modeLivraison, statusLivraison) "
                    + "VALUES(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, cmd.getClient().getId());
            ps.setString(2, cmd.getDate());
            ps.setDouble(3, cmd.getMontant());
            ps.setString(4, cmd.getModePaiement());
            ps.setString(5, cmd.getStatutPaiement());
            ps.setString(6, cmd.getModeLivraison());
            ps.setString(7, cmd.getStatutLivraison());

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
    public void deleteCmd(int idCmd) {
        con = dao.getConnection();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement("DELETE FROM Commande WHERE idCmd=?");

            ps.setInt(1, idCmd);

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
    public void editCmd(Commande cmd) {
        con = dao.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Commande SET idClient = '" + cmd.getClient().getId()
                    + "',date='" + cmd.getDate()
                    + "',montant='" + cmd.getMontant()
                    + "',modePaiement='" + cmd.getModePaiement()
                    + "',statusPaiement='" + cmd.getStatutPaiement()
                    + "',modeLivraison='" + cmd.getModeLivraison()
                    + "',statusLivraison='" + cmd.getStatutLivraison()
                    + "' WHERE idCmd=" + cmd.getIdCmd();
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
    public Commande getCmd(int id) {
        Commande cmd = new Commande();
        clientDao = new ImplementationInterfaceClient(DAOFactory.getInstance());
        con = dao.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM Commande WHERE idCmd=?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                cmd.setIdCmd(rs.getInt("idCmd"));

                cmd.setClient(new Client());
                cmd.getClient().setId(rs.getInt("idClient"));

                cmd.setDate(rs.getString("date"));
                cmd.setMontant(rs.getDouble("montant"));
                cmd.setModePaiement(rs.getString("modePaiement"));
                cmd.setStatutPaiement(rs.getString("statusPaiement"));
                cmd.setModeLivraison(rs.getString("modeLivraison"));
                cmd.setStatutLivraison(rs.getString("statusLivraison"));
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
        cmd.setClient(clientDao.getClient(cmd.getClient().getId()));
        return cmd;
    }

    @Override
    public List<Commande> getAllCmds() {
        con = dao.getConnection();
        clientDao = new ImplementationInterfaceClient(DAOFactory.getInstance());
        List<Commande> list = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM Commande");

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Commande(
                        rs.getInt("idCmd"),
                        rs.getInt("idClient"),
                        rs.getString("date"),
                        rs.getDouble("montant"),
                        rs.getString("modePaiement"),
                        rs.getString("statusPaiement"),
                        rs.getString("modeLivraison"),
                        rs.getString("statusLivraison")));
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

        for (Commande c : list) {
            c.setClient(clientDao.getClient(c.getClient().getId()));
        }

        return list;
    }

    @Override
    public List<Commande> getAllCmdsOfThisMonth(String month) {
        con = dao.getConnection();
        clientDao = new ImplementationInterfaceClient(DAOFactory.getInstance());
        List<Commande> list = new ArrayList<>();

        Statement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Commande WHERE date LIKE '%/" + month + "/%'";
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new Commande(
                        rs.getInt("idCmd"),
                        rs.getInt("idClient"),
                        rs.getString("date"),
                        rs.getDouble("montant"),
                        rs.getString("modePaiement"),
                        rs.getString("statusPaiement"),
                        rs.getString("modeLivraison"),
                        rs.getString("statusLivraison")));
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
                if (st != null) {
                    st.close();
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
        for (Commande c : list) {
            c.setClient(clientDao.getClient(c.getClient().getId()));
        }
        return list;
    }

    @Override
    public List<Commande> getAllCmdsOfThisClient(Client cl) {
        con = dao.getConnection();
        clientDao = new ImplementationInterfaceClient(DAOFactory.getInstance());
        List<Commande> list = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Commande WHERE idClient = " + cl.getId() + "";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Commande(
                        rs.getInt("idCmd"),
                        rs.getInt("idClient"),
                        rs.getString("date"),
                        rs.getDouble("montant"),
                        rs.getString("modePaiement"),
                        rs.getString("statusPaiement"),
                        rs.getString("modeLivraison"),
                        rs.getString("statusLivraison")));
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

        for (Commande c : list) {
            c.setClient(clientDao.getClient(c.getClient().getId()));
        }

        return list;
    }

    @Override
    public List<Commande> getAllCmdsOfLast24h() {
        List<Commande> list = new ArrayList<>();

        for (Commande c : this.getAllCmds()) {
            try {
                SimpleDateFormat dbDateParser = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date dbDate = dbDateParser.parse(c.getDate());
                if (dbDate.after(LocalDateTime.now().minusDays(1).toDate())) {
                    list.add(c);
                }
            } catch (ParseException ex) {
                Logger.getLogger(ImplementationInterfaceCommande.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public List<Commande> getShippedCmdsOfThisMonth(String month) {
        con = dao.getConnection();
        clientDao = new ImplementationInterfaceClient(DAOFactory.getInstance());
        List<Commande> list = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Commande WHERE statusLivraison = 'Shipped' AND date LIKE '%/" + month + "/%'";
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new Commande(
                        rs.getInt("idCmd"),
                        rs.getInt("idClient"),
                        rs.getString("date"),
                        rs.getDouble("montant"),
                        rs.getString("modePaiement"),
                        rs.getString("statusPaiement"),
                        rs.getString("modeLivraison"),
                        rs.getString("statusLivraison")));
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
                if (st != null) {
                    st.close();
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

        for (Commande c : list) {
            c.setClient(clientDao.getClient(c.getClient().getId()));
        }

        return list;
    }

}
