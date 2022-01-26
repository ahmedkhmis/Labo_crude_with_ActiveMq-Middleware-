/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo_crude;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ben moussa imene
 */
public class Menu extends JPanel{

    private final JLabel nom1;
    private final JLabel boite;
    private final JLabel qte;
    private final JLabel prix;
    private final JLabel id;
    private final JPanel pn;
    private final JPanel pn1;
    
    	public Menu(String nom) {
    setLayout(new BorderLayout());
    nom1=new JLabel("Nom:   "+nom);
    nom1.setFont(new Font(Font.MONOSPACED, Font.BOLD|Font.ITALIC, 24));
     
     Labo_crude lb=new Labo_crude();
     Medicament med=lb.RechercheParNom(nom);
     id=new JLabel("ID: "+med.getId_med());
     boite=new JLabel("Le medicament est composé de:   "+med.getBoite());
     boite.setFont(new Font(Font.MONOSPACED, Font.BOLD|Font.ITALIC, 24));
     qte=new JLabel("La quantité :   "+med.getQuantite());
     qte.setFont(new Font(Font.MONOSPACED, Font.BOLD|Font.ITALIC, 24));
     prix=new JLabel("Le prix :   "+med.getPrix());
     prix.setFont(new Font(Font.MONOSPACED, Font.BOLD|Font.ITALIC, 24));
        
   id.setFont(new Font(Font.MONOSPACED, Font.BOLD|Font.ITALIC, 24));
     pn = new JPanel();
     pn.setAlignmentX(JPanel.CENTER_ALIGNMENT);
     pn1=new JPanel();
        JPanel pn2 = new JPanel();
        JPanel pn3 = new JPanel();
     pn1.add(id);
     pn1.setBackground(Color.green);
         
     pn.add(boite);
     pn.setLayout(new GridLayout(4, 0));
     
                pn.add(nom1);
	pn.add(boite);
     
                pn.add(qte);
		pn.add(prix);
                        pn2.add(new JLabel());
		pn3.add(new JLabel());
     
                this.add(pn2,BorderLayout.WEST);
     		this.add(pn3,BorderLayout.EAST);
                this.add(pn1,BorderLayout.NORTH);
     		this.add(pn,BorderLayout.CENTER);
		
		
     
     
        }

    
}
