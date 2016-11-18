/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Bean.PersonneBean;
import Persistence.UserBdd;
import domaine.Personne;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Connection extends JPanel implements ActionListener {

    static JTextField TFPseudo;
    JButton boutonConnection;

    static String Pseudo;

    public Connection() {
        setLayout(null);
        setPreferredSize(new Dimension(500, 300));
        boutonConnection = new JButton("Connect");
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);
        TFPseudo = new JTextField();
        TFPseudo.setBackground(new Color(255, 255, 255));
        TFPseudo.setForeground(new Color(0, 0, 0));
        TFPseudo.setBounds(150, 50, 200, 30);

        p1.add(boutonConnection);
        boutonConnection.setBounds(150, 180, 150, 20);
        boutonConnection.addActionListener(this);

        p1.add(boutonConnection);
        p1.add(TFPseudo);
        p1.setBounds(0, 0, 500, 300);
        add(p1);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Connect")) {
            Pseudo = TFPseudo.getText();
            try {
                Personne personne = PersonneBean.getInstance().getPersonneInfo(Pseudo);
                if (personne != null) {
                    MyFrame.getInstance().getFrame().dispose();
                    MyFrame.getInstance().setFrame(new JFrame("TP4 Infos"));                    
                    MyFrame.getInstance().changeFrame(new InfoDisplay());
                } else {
                    MyFrame.getInstance().changeFrame(new WrongLogin());
                }
            } catch (Exception err) {
                System.out.println(err);
            }
        }
    }

}
