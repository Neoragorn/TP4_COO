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

/**
 *
 * @author sofian
 */
public class UserBdd {

    private static final Connection conn = PersistenceConnection.getInstance().getConn();

    public static Personne connectUser(String id) throws SQLException {
        String req = "SELECT idPersonne, nom, prenom, evaluation FROM Personne WHERE idPersonne = ?";
        try {
            PreparedStatement pss = conn.prepareStatement(req);
            pss.setInt(1, Integer.parseInt(id));
            ResultSet rs = pss.executeQuery();
            rs.next();
            Personne personne = new Personne(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            return personne;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    /*    public static User getUser(String pseudo) throws SQLException, NoSuchAlgorithmException {
        try {
            User user = new User();
            String req = "SELECT * FROM User WHERE pseudo = ? AND password = ? ";
            PreparedStatement pss = conn.prepareStatement(req);

            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] result = mDigest.digest(pwd.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            pss.setString(1, pseudo);
            pss.setString(2, sb.toString());
            ResultSet rs = pss.executeQuery();
            rs.next();
            user.setIdUser(rs.getInt(1));
            user.setPseudo(rs.getString(2));
            user.setMail(rs.getString(4));
            return user;
        } catch (SQLException | NoSuchAlgorithmException e) {
            if (e instanceof SQLException) {
                System.out.println("Erreur dans le login et/ou le mot de passe");
                return null;
            } else {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*    public static void updateUser(User user) {
        
    }*/
}
