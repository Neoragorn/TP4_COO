/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import domaine.Personne;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sofian
 */
public class VirtualUserProxy extends ArrayList<Personne> {

    Personne pere = null;
    ArrayList<Personne> inst = null;

    public VirtualUserProxy(int id) {

    }

    public ArrayList<Personne> getInst() {
        return inst;
    }

    public void setInst(ArrayList<Personne> inst) {
        this.inst = inst;
    }

    public ArrayList<Personne> fillPersonneList(int id) {
        if (inst == null) {
            inst = PersonneBdd.getPersonnelByPersonne(id);
        }
        // garnir listp a partir de id et du datamapper
        return inst;
    }

    public Personne addPere(int id) {
        System.out.println("test deux");
        if (pere == null) {
            pere = PersonneBdd.setPere(id);
        }
        return pere;
    }
}
