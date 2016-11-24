/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Bean.PersonneBean;
import domaine.Personne;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sofian
 */
public class InfoDisplay extends JPanel implements ActionListener, ListSelectionListener {

    JButton validation;
    JButton annuler;
    Personne personne;

    private DefaultListModel listFils;
    private JList fils;

    private DefaultListModel listEvalFils;
    private JList evalFils;

    private JLabel vous;
    private JLabel votrePere;
    private JLabel votreEval;
    private JLabel vosFils;
    private JLabel evalDe;

    public InfoDisplay() {
        MyFrame.getInstance().getFrame().repaint();
        setLayout(null);
        setPreferredSize(new Dimension(800, 400));

        this.personne = PersonneBean.getInstance().getPersonne();

        vous = new JLabel("vous : " + personne.getNom() + " " + personne.getPrenom());
        if (personne.getPere() == null) {
            votrePere = new JLabel("votre Pere : Aucun");
        } else {
            votrePere = new JLabel("votre Pere : " + this.personne.getPere().getNom() + " " + this.personne.getPere().getPrenom());
        }
        votreEval = new JLabel("votre Evaluation : " + personne.getEvaluation());
        vosFils = new JLabel("vos Fils : ");
        evalDe = new JLabel("evaluation de : ");

        vous.setOpaque(true);
        vous.setBounds(20, 30, 300, 20);

        votrePere.setOpaque(true);
        votrePere.setBounds(20, 50, 300, 20);

        votreEval.setOpaque(true);
        votreEval.setBounds(20, 80, 300, 20);

        vosFils.setOpaque(true);
        vosFils.setBounds(20, 110, 300, 20);

        evalDe.setOpaque(true);
        evalDe.setBounds(300, 140, 300, 20);

        validation = new JButton("Changer Evaluation");
        annuler = new JButton("Annuler");

        listFils = new DefaultListModel();
        listEvalFils = new DefaultListModel();

        ArrayList<Personne> personneList = this.personne.getFils();
        for (Personne p : personneList) {
//            Personne p = PersonneBean.getInstance().getPersonneInfo(personne.getId());
            listFils.addElement(p.getNom() + " " + p.getPrenom());
            listEvalFils.addElement(p.getNom() + " " + p.getPrenom() + " : " + p.getEvaluation());
        }

        fils = new JList(listFils);
        fils.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fils.setSelectedIndex(0);
        fils.addListSelectionListener(this);
        fils.setVisibleRowCount(5);

        evalFils = new JList(listEvalFils);
        evalFils.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        evalFils.setSelectedIndex(0);
        evalFils.addListSelectionListener(this);
        evalFils.setVisibleRowCount(5);

        JScrollPane scrollFils = new JScrollPane(fils);
        scrollFils.setBounds(40, 150, 250, 200);

        JScrollPane scrollEvalFils = new JScrollPane(evalFils);
        scrollEvalFils.setBounds(500, 150, 250, 200);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);
        annuler.setBounds(600, 50, 100, 20);
        annuler.addActionListener(this);
        validation.setBounds(500, 350, 200, 20);
        validation.addActionListener(this);

        p1.add(scrollFils);
        p1.add(scrollEvalFils);
        p1.add(vous);
        p1.add(votrePere);
        p1.add(votreEval);
        p1.add(vosFils);
        p1.add(evalDe);
        p1.add(validation);
        p1.add(annuler);
        p1.setBounds(0, 0, 800, 400);

        add(p1);
    }

    public void valueChanged(ListSelectionEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Annuler")) {
            MyFrame.getInstance().changeFrame(new Connection());
        }
        if (e.getActionCommand().equals("Changer Evaluation")) {
            MyFrame.getInstance().changeFrame(new ChangeEval(evalFils.getSelectedValue()));
        }
    }
}
