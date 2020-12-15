/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entity.Etat;
import Entity.Ticket;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jbuffeteau
 */
public class FonctionsMetier implements IMetier
{
    @Override
    public User GetUnUser(String login, String mdp)
    {
        try {
            User use = null;
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select idUser, nomUser from users where loginUser = ? and pwdUser = ?");
            ps.setString(1, login);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();
            rs.next();
            use = new User(rs.getInt("idUser"), rs.getString("nomUser"));
            ps.close();
            
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Ticket> GetAllTickets()
    {
        try {
            ArrayList<Ticket> lesTickets = new ArrayList<>();
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select idTicket, nomTicket from ticket");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Ticket tic = new Ticket(rs.getInt("idTicket"), rs.getString("nomTicket"));
                lesTickets.add(tic);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesTickets;
    }

    @Override
    public ArrayList<Ticket> GetAllTicketsByIdUser(int idUser)
    {
        try {
            ArrayList<Ticket> lesTickets = new ArrayList<>();
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select idTicket, nomTicket from ticket where idTicket = "+idUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Ticket tic = new Ticket(rs.getString("idTicket"), rs.getString("nomTicket"));
                lesTickets.add(tic);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesTickets;
    }

    @Override
    public void InsererTicket(int idTicket, String nomTicket, String dateTicket, int idUser, int idEtat) 
    {
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("insert into ticket values('"+idUser+"','"+idEtat+"','"+idTicket+"','"+nomTicket+"',"+ "0)");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

    @Override
    public void ModifierEtatTicket(int idTicket, int idEtat) 
    {
        
            
        
    }

    @Override
    public ArrayList<User> GetAllUsers()
    {
        try {
            ArrayList<User> lesUsers = new ArrayList<>();
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select idUser, nomUser from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                User use = new User(rs.getInt("idUser"), rs.getString("nomUser"));
                lesUsers.add(use);
            }
            ps.close();
                
            return lesUsers;   
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesUsers;
    }

    @Override
    public int GetLastIdTicket()
    {
        
        return 0;
    }

    @Override
    public int GetIdEtat(String nomEtat)
    {
        
        return 0;
    }

    @Override
    public ArrayList<Etat> GetAllEtats()
    {
        try {
            ArrayList<Etat> lesEtats = new ArrayList<>();
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select idEtat, nomEtat from etats");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Etat et = new User(rs.getInt("idEtat"), rs.getString("nomEtat"));
                lesEtats.add(et);
            }
            ps.close();
            return null;    
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
