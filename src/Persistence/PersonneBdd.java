/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import domaine.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sofian
 */
public class PersonneBdd {

    private static final Connection conn = PersistenceConnection.getInstance().getConn();

    public static Personne connectPersonne(int id) throws SQLException {
        String req = "SELECT idPersonne, nom, prenom, evaluation FROM Personne WHERE idPersonne = ?";
        try {
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, id);
            ResultSet rs = pss.executeQuery();
            rs.next();
            Personne personne = new Personne(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            //creer une PersonneFactory avec le bon id
            //cree le virtualproxy (a partir du virtualproxybuilder generique et du personfactory)
            VirtualUserProxy virtual = new VirtualUserProxy(personne.getId());
            virtual.fillPersonneList(id);
            personne.setVirtualFils(virtual.getInst());
            personne.setPere(virtual.addPere(id));
            return personne;
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Personne> getPersonnelByPersonne(int id) {
        ArrayList<Personne> listp = new ArrayList();
        String req = "SELECT p.idPersonne, nom, prenom, evaluation FROM Personnel pe JOIN Personne p ON pe.idPersonne = p.idPersonne WHERE idPere = ?;";
        try {
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, id);
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                Personne p = new Personne(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                listp.add(p);
            }
            return listp;
        } catch (Exception e) {
            return null;
        }
    }

    public static void updateEval(String eval, int id) {
        String req = "UPDATE Personne SET evaluation = ? WHERE idPersonne = ?";
        try {
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setString(1, eval);
            pss.setInt(2, id);
            pss.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Personne setPere(int id) {
        String req = "SELECT p.idPersonne, nom, prenom, evaluation FROM Personne p JOIN Personnel pe ON pe.idPere = p.idPersonne WHERE pe.idPersonne = ?;";
        try {
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, id);
            ResultSet rs = pss.executeQuery();
            rs.next();
            Personne p = new Personne(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            return p;
        } catch (Exception e) {
            return null;
        }
    }

}
