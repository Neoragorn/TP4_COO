package Bean;

import Persistence.PersonneBdd;
import domaine.Personne;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sofian
 */
public class PersonneBean {

    Personne personne = null;

    public PersonneBean() {

    }

    public static PersonneBean inst;

    static public PersonneBean getInstance() {
        if (inst == null) {
            inst = new PersonneBean();
        }
        return inst;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Personne getPersonneInfo(int id) {
        try {
            Personne personne = PersonneBdd.connectPersonne(id);
            this.personne = personne;
            return personne;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void changeEval(String eval, String ancientEval) {
        try {
            for (Personne fils : this.personne.getFils()) {
                if (fils.getEvaluation().equals(ancientEval)) {
                    PersonneBdd.updateEval(eval, fils.getId());
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
