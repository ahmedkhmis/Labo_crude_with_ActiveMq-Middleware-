/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo_crude;

/**
 *
 * @author ahmed
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.apache.activemq.ActiveMQConnectionFactory;

//import org.apache.activemq.ActiveMQConnectionFactory;
//import projet_midd.QueueCosumer;


public class Demandes extends JFrame implements ActionListener{
    public  int compt;
   // ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");



	final DefaultTableModel model = new DefaultTableModel();
       // public String[] arr;
	private JTable tab;
	private JButton annuler;
    private JButton btn;
    private JButton btn_rfs;
    private String registre;
        
	public Demandes()  {
              this.setTitle("Les Demandes");
		this.setMaximumSize(new Dimension(1000,300));
		this.setSize(new Dimension(1000,300));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
                tab=new JTable(model);
		model.addColumn("Nom de pharmacie");
		model.addColumn("Adresse de pharmacie");
		model.addColumn("Nom de medicament");
                model.addColumn("Quantité demandé");
                model.addColumn("Accepter");
                model.addColumn("Refuser");
                tab.getColumn("Accepter").setCellRenderer(new ButtonRenderer());
                tab.getColumn("Accepter").setCellEditor(new ButtonEditor(new JCheckBox()));
                tab.getColumn("Refuser").setCellRenderer(new ButtonRenderer1());
                tab.getColumn("Refuser").setCellEditor(new ButtonEditor1(new JCheckBox()));
                
                //this.run();
              
          
                
               
                
           
		
	   JScrollPane scroll = new JScrollPane(tab);
	   add(scroll);
	   annuler=new JButton("OK");
	   add(annuler,BorderLayout.SOUTH);
	   annuler.addActionListener(this);
           compt=model.getRowCount();
	   setVisible(true);
            
		
	}
	
	
  /*  public static void main(String[] args) {
        new Demandes();
    }*/
public void CreerRow(String[] arr1){
    registre=null;
    Object ob[]=new Object[4];
                Pharmacie p=new Pharmacie();
                String[]p_array=new String[2];
                p_array=p.RechercheParId(Integer.parseInt(arr1[0]));
                ob[0]=p_array[0];
                registre=p_array[0];
                ob[1]=p_array[1];
                ob[2]=arr1[2];
                ob[3]=arr1[3];
                btn=new JButton("Confirmer");
                btn_rfs=new JButton("Annuler");
                btn.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent event)
        {
                ActiveMQConnectionFactory connectionFactory = new
       ActiveMQConnectionFactory("tcp://localhost:61616");
        javax.jms.Connection connection;
        try {
             connection= connectionFactory.createConnection();
             connection.start();
             Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             Destination destination = session.createQueue("enset.reponse");
             MessageProducer producer= session.createProducer(destination);
             producer.setDeliveryMode(DeliveryMode.PERSISTENT);
             ObjectMessage ObjMessage= session.createObjectMessage();         
           
            ObjMessage.setObject("Votre demande a confirmée");
            producer.send(ObjMessage);
            session.close();
            connection.close();
            model.removeRow(0);
        } catch (JMSException ex) {
        }               
            }
        }
      
    );
                 btn_rfs.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent event)
        {
                ActiveMQConnectionFactory connectionFactory = new
       ActiveMQConnectionFactory("tcp://localhost:61616");
        javax.jms.Connection connection;
        try {
             connection= connectionFactory.createConnection();
             connection.start();
             Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             Destination destination = session.createQueue("enset.reponse");
             MessageProducer producer= session.createProducer(destination);
             producer.setDeliveryMode(DeliveryMode.PERSISTENT);
             ObjectMessage ObjMessage= session.createObjectMessage();   
            // ObjMessage.setObjectProperty("nom",registre+"");
           
            ObjMessage.setObject("Votre demande a refusée car le stocke est insuffisant");
            producer.send(destination, ObjMessage);
            session.close();
            connection.close();
            model.removeRow(0);
        } catch (JMSException ex) {
        }               
            }
        }
      
    );
              
                model.addRow(ob);

}
 
    @Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
	}
        
        

class ButtonRenderer extends JButton implements TableCellRenderer 
  {
    public ButtonRenderer() {
      setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
      setText((value == null) ? "Confirmer" : value.toString());
      return this;
    }
  }
  class ButtonEditor extends DefaultCellEditor 
  {
    private String label;
    
    public ButtonEditor(JCheckBox checkBox)
    {
      super(checkBox);
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) 
    {
      label = (value == null) ? "Confirmer" : value.toString();
      btn.setText(label);
      return btn;
    }
    public Object getCellEditorValue() 
    {
      return new String(label);
    }
  }
  
  class ButtonRenderer1 extends JButton implements TableCellRenderer 
  {
    public ButtonRenderer1() {
      setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
      setText((value == null) ? "Annuler" : value.toString());
      return this;
    }
  }
  class ButtonEditor1 extends DefaultCellEditor 
  {
    private String label;
    
    public ButtonEditor1(JCheckBox checkBox)
    {
      super(checkBox);
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) 
    {
      label = (value == null) ? "Annuler" : value.toString();
      btn_rfs.setText(label);
      return btn_rfs;
    }
    public Object getCellEditorValue() 
    {
      return new String(label);
    }
  }
  
  
 
}

