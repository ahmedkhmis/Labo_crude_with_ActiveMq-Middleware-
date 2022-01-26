/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo_crude;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import static java.util.Collections.list;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import org.apache.activemq.ActiveMQConnectionFactory;
import projet_midd.QueueCosumer;

/**
 *
 * @author ben moussa imene
 */
public class consulte_med extends JFrame {
JButton btnvalider;
	JLabel lblnom;
	JTextField tfnom;
	
	JLabel lhelp;
	JSplitPane jsp;
	JList<Object> jl;
	DefaultListModel<Object> model;
	private JTabbedPane jtp;
	private JPanel pn1;
	private JPanel pn2;
	private JLabel bienvenu;

	private DefaultListModel modelee;
	private JList liste;
    private final JLabel lblboite;
    private final JTextField tfboite;
    private final JLabel lblquantite;
    private final JTextField tfquantite;
    private final JLabel lblprix;
    private final JTextField tfprix;
    private final JList<Object> list;
    private final JTabbedPane tbbed;
    private final JLabel lblid;
    private final JTextField tfid;

    public consulte_med() {
        this.setTitle("consulter medicament");
		setSize(1400,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setLayout(new BorderLayout());
               
                
		lblid=new JLabel("ID medicament");

		tfid=new JTextField("X X X X X",15);
		tfid.setHorizontalAlignment(JTextField.CENTER);
                
                lblnom=new JLabel("Nom medicament");

		tfnom=new JTextField("taper le nom",15);
		tfnom.setHorizontalAlignment(JTextField.CENTER);

		lblboite=new JLabel("boite");
		tfboite=new JTextField("9999",15);
		tfboite.setHorizontalAlignment(JTextField.CENTER);

		lblquantite=new JLabel("quantite");
		tfquantite=new JTextField("9999",15);
		tfquantite.setHorizontalAlignment(JTextField.CENTER);
                lblprix=new JLabel("prix");
		tfprix=new JTextField("9999",15);
		tfprix.setHorizontalAlignment(JTextField.CENTER);
		btnvalider=new JButton("Ajouter");
                
		JPanel pn=new JPanel();
		pn.setLayout(new FlowLayout());
                pn.add(lblid);
                pn.add(tfid);

		pn.add(lblnom);
		pn.add(tfnom);
		pn.add(lblboite);
		pn.add(tfboite);
		pn.add(lblquantite);
		pn.add(tfquantite);
                pn.add(lblprix);
		pn.add(tfprix);
		pn.add(btnvalider);
                /////////actions///////////
                btnvalider.addActionListener(new validerEcoteur());
                tfid.addFocusListener(new TxtEcouteur());
                tfnom.addFocusListener(new TxtEcouteur());
		tfboite.addFocusListener(new TxtEcouteur());
		tfquantite.addFocusListener(new TxtEcouteur());
                tfprix.addFocusListener(new TxtEcouteur());
                
               jsp=new JSplitPane();
		model=new DefaultListModel<>();
                 Labo_crude lb=new Labo_crude();
                ResultSet rs=lb.getallMed();
		lb.afficheMed(rs, model);
		list=new JList<>(model);
		list.setPreferredSize(new Dimension(280,800));
		jsp.setLeftComponent(list);
		tbbed=new JTabbedPane();
		jsp.setRightComponent(tbbed);
		add(jsp,BorderLayout.CENTER);
                list.addMouseListener(new ListEcoteur());
               
                
                lhelp=new JLabel("help:");
                pn2=new JPanel();
                pn2.setLayout(new GridLayout(0,3,20,20));
                
                
		pn2.add(lhelp);
	

		add(pn2,BorderLayout.SOUTH);

		this.add(jsp);
                this.add(pn,BorderLayout.NORTH);
                
                

		
              
 this.setVisible(true);
		
    }
    	class validerEcoteur implements ActionListener{
		public boolean chaine_blanc(String s) {
			boolean t=false;
			for(int i = 0;i<s.length();i++) {
				if(s.charAt(i)==' ') {
					t=true;				
				}
				else {
					t=false;
				}
			}		
			return t;
		}
                public boolean nom_existepas(String s) {
			boolean existe=true;
			if(model.getSize()>0) {
				for(int i=0;i<model.getSize();i++) {
					if(s.equals(model.getElementAt(i)+"")) {
						existe=false;
					}
				}}

			return existe;
		}


		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnvalider) {
				boolean b = tfid.getText().equals("X X X X X") ||tfnom.getText().equals("taper le nom") || tfboite.getText().equals("9999") || tfquantite.getText().equals("9999")|| tfprix.getText().equals("9999");
				if(b||chaine_blanc(tfid.getText())||chaine_blanc(tfnom.getText())||chaine_blanc(tfboite.getText())||chaine_blanc(tfquantite.getText())||chaine_blanc(tfprix.getText())) {
					
					JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires" ,"Données manquantes",1);
				}
				else {
					//model.addElement(tfnom.getText());
                                        Labo_crude lb=new Labo_crude();

					if(nom_existepas(tfnom.getText())) {
                                            Medicament med=new Medicament(Integer.parseInt(tfid.getText()),tfnom.getText(), Integer.parseInt(tfboite.getText()),Integer.parseInt(tfquantite.getText()) ,Integer.parseInt(tfprix.getText()));

						lb.AjouterMedicament(med);


						model.addElement(tfnom.getText());
                                                tfid.setText("X X X X X");
						tfnom.setText("taper le nom");
						tfboite.setText("9999");
						tfquantite.setText("9999");
                                                tfprix.setText("9999");

					}
					
					
				}
			}

		}

	}

    class ListEcoteur implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton()==e.BUTTON1)
			{
				System.out.println(e.getSource());
				if(e.getClickCount()==2)
				{
					 
					tbbed.addTab(list.getSelectedValue()+"", new Menu(list.getSelectedValue()+""));
				}
			}
			if(e.getButton()==e.BUTTON3) {
				JPopupMenu popm=new JPopupMenu("Edite");
				JMenuItem supprimer=new JMenuItem("Supprimer");
				JMenuItem renommer=new JMenuItem("Renommer");
				
				popm.add(supprimer);
                                popm.add(renommer);
				
				popm.add(new JSeparator());
				popm.add(renommer);
                                supprimer.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==supprimer) {
                                                        Labo_crude lb=new Labo_crude();
                                                       
							lb.Supprimer(list.getSelectedValue()+"");
							//System.out.println(list.getSelectedIndex()(list.getSelectedValue()+""));
							model.removeElementAt(list.getSelectedIndex());
							for(int i=0;i<tbbed.getComponentCount()-1;i++)
							{
								if(tbbed.getTitleAt(i).equals(list.getSelectedValue()+"")) {
									tbbed.removeTabAt(i);
								}
							}
							
							//tbbed.removeTabAt(tbbed.getSelectedIndex());						
							}
					

					}
				});
                                		renommer.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==renommer) {
							
                                                        Labo_crude lb=new Labo_crude();
							 ListSelectionModel selmodel = list.getSelectionModel();
							 
				                int index = selmodel.getMinSelectionIndex();
				                if (index == -1) {
				                    return;
				                }

				                Object item = model.getElementAt(index);
				                String pseudonv = JOptionPane.showInputDialog("Rename item", item);
				                String newitem = null;

				                if (pseudonv != null) {
				                    newitem =pseudonv.trim();
				                } else {
				                    return;
				                }

				                if (!newitem.isEmpty()) {
				                    model.remove(index);
				                    model.add(index, newitem);
				                }
				                System.out.println(pseudonv);
				                //System.out.println(sel);
				                if(tbbed.getSelectedIndex()>=0) {
				                tbbed.setTitleAt(tbbed.getSelectedIndex(), pseudonv);//(tbbed.getSelectedIndex());
				                }
				                lb.Modifier_nom(list.getSelectedValue()+"", pseudonv+"");
							 
							 
							 
						}
						
					}
				});
				

			}

		}
				
				//popm.show(list,e.getX(),e.getY());

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }
				
			}
			
		

		
		
	
    class TxtEcouteur implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
                    if (tfid.getText().equals("X X X X X")&&e.getSource()==tfid) {
				tfid.setText("");
				lhelp.setText("Help:    Entrez l'identifient de medicament");}
			if (tfnom.getText().equals("taper le nom")&&e.getSource()==tfnom) {
				tfnom.setText("");
				lhelp.setText("Help:    Entrez votre nom de medicament ");


			}
			if (tfboite.getText().equals("9999")&&e.getSource()==tfboite) {
				tfboite.setText("");				
				lhelp.setText("Help:    Entrez numero du boite de medicament ");

			}
			if (tfquantite.getText().equals("9999")&&e.getSource()==tfquantite) {
				tfquantite.setText("");
				lhelp.setText("Help:     Entrez la quantité à ajouter pour cette medicament ");
			}
                        if (tfprix.getText().equals("9999")&&e.getSource()==tfprix) {
				tfprix.setText("");
				lhelp.setText("Help:     Entrez le prix ");
			}

		}

		@Override
		public void focusLost(FocusEvent e) {
                    if (tfid.getText().equals("")&&e.getSource()==tfid) {
				tfid.setText("X X X X X");
				lhelp.setText("Help:");
			}
			if (tfnom.getText().equals("")&&e.getSource()==tfnom) {
				tfnom.setText("taper le nom");
				lhelp.setText("Help:");
			}
			if (tfboite.getText().equals("")&&e.getSource()==tfboite) {
				tfboite.setText("9999");lhelp.setText("Help:");
			}
			if (tfquantite.getText().equals("")&&e.getSource()==tfquantite) {
				tfquantite.setText("9999");lhelp.setText("Help:");
			}
                        if (tfprix.getText().equals("")&&e.getSource()==tfprix) {
				tfprix.setText("9999");lhelp.setText("Help:");
			}

		}



	}
   /* public static void main(String[] args) {
        new consulte_med();
        
    }*/
    
    
}
