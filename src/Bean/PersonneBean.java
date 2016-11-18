package Bean;

import Persistence.UserBdd;
import domaine.Personne;

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
    
    public Personne getPersonneInfo(String id)
    {
        try
        {
            Personne personne = UserBdd.connectUser(id);
            this.personne = personne;
            return personne;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
