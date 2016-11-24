/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Bean.PersonneBean;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sofian
 */
public class ChangeEval extends JPanel implements ActionListener {

    JButton validation;
    private JTextField TFEval;
    private String eval;
    private String[] realEval;
    
    public ChangeEval(Object o) {
        setLayout(null);
        setPreferredSize(new Dimension(800, 400));
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setOpaque(false);
        String evalParsed = (String)o;
        realEval = evalParsed.split(": ");
        TFEval = new JTextField();
        TFEval.setBounds(200, 50, 200, 30);
        
        validation = new JButton("Valider");
        validation.setBounds(350, 300, 100, 20);
        validation.addActionListener(this);

        p1.add(validation);
        p1.add(TFEval);
        p1.setBounds(0, 0, 800, 400);

        add(p1);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Valider")) {
            eval = TFEval.getText();
            PersonneBean.getInstance().changeEval(eval, realEval[1]);
            //Je devrais normalement faire ici une mise à jour de l'affichage avec les nouvelles données de la BDD
            MyFrame.getInstance().changeFrame(new InfoDisplay());
        }
    }
}
