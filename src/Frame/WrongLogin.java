/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;


import Bean.PersonneBean;
import static Frame.Connection.id;
import Persistence.PersonneBdd;
import domaine.Personne;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sofian
 */
public class WrongLogin extends JPanel implements ActionListener {

    static JTextField TFPseudo;
    JButton boutonConnection;

    static String id;

    private JLabel wrongMessage;

    public WrongLogin() {
        setLayout(null);
        setPreferredSize(new Dimension(500, 300));

        boutonConnection = new JButton("Connect");
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);
        TFPseudo = new JTextField("Id");
        TFPseudo.setBackground(new Color(255, 255, 255));
        TFPseudo.setForeground(new Color(0, 0, 0));
        TFPseudo.setBounds(150, 50, 200, 30);

        p1.add(boutonConnection);
        boutonConnection.setBounds(150, 180, 150, 20);
        boutonConnection.addActionListener(this);

        wrongMessage = new JLabel("Wrong login. Try again");
        wrongMessage.setOpaque(true);
        wrongMessage.setBounds(150, 150, 300, 20);

        p1.add(boutonConnection);
        p1.add(TFPseudo);
        p1.add(wrongMessage);
        p1.setBounds(0, 0, 500, 300);
        add(p1);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Connect")) {
            id = TFPseudo.getText();
            try {
                Personne personne = PersonneBdd.connectPersonne(Integer.parseInt(id));
                if (personne != null) {
                    PersonneBean.getInstance().setFils(personne);
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
