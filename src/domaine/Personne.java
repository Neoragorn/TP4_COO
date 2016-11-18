/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import java.util.ArrayList;

/**
 *
 * @author sofian
 */
public class Personne {

    private int id;
    private String nom;
    private String prenom;
    private String evaluation;
    private ArrayList<Personne> fils = new ArrayList();

    public Personne(int id, String nom, String prenom, String evaluation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.evaluation = evaluation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }    
    
}
