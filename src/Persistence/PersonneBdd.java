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
            return personne;
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Personne> getIdPersonnelByPersonne(Personne personne) {
        ArrayList<Personne> listp = new ArrayList();
        String req = "SELECT idPersonne FROM Personnel WHERE idPere = ?";
        try {
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, personne.getId());
            ResultSet rs = pss.executeQuery();
            while (rs.next()) {
                Personne p = new Personne(rs.getInt(1));
                listp.add(p);
            }
            return listp;
        } catch (Exception e) {
            return null;
        }
    }
}
