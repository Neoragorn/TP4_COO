/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

import Frame.Connection;
import Frame.MyFrame;
import Persistence.PersistenceConnection;
import javax.swing.JPanel;

/**
 *
 * @author sofian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PersistenceConnection co = new PersistenceConnection();        
        try {
            co.startConnection("casier", "C&?1+mur");
        } catch (Exception e) {
            System.out.println(e);
        }

        MyFrame myF = new MyFrame();
        MyFrame.setInst(myF);        
        JPanel jp = new Connection();
        MyFrame.getInstance().setActualPanel(jp);
        //Ecran pour soit se connecter, soit s'inscrire
        myF.startPoint(jp);
    }
    
}
