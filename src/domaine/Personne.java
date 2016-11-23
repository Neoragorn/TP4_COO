/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import Persistence.VirtualUserProxy;
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
    private Personne pere = null;
    private ArrayList<Personne> fils = new ArrayList();

    public Personne() {

    }

    public Personne(int id) {
        this.id = id;
    }

    public Personne(int id, String nom, String prenom, String evaluation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.evaluation = evaluation;
    }

    public ArrayList<Personne> getFils() {
        return fils;
    }

    public void setFils(ArrayList<Personne> fils) {
        this.fils = fils;
    }
    
      public void setVirtualFils(ArrayList<Personne> virtual) {
        this.fils = virtual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personne getPere() {
        return pere;
    }

    public void setPere(Personne pere) {
        this.pere = pere;
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
