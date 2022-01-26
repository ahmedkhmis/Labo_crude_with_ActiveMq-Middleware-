/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo_crude;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.activemq.ActiveMQConnectionFactory;



/**
 *
 * @author hamdi
 */
public class fenetre extends  JFrame{

    private JLabel idph,idmed,nom,quant,titre,titre1;
    private JTextField txt_idp,txt_idmed,quant_txt,nom_txt;
    private String i1,i2,i3,i4,i5;
    private final JLabel lb;
    private JLabel env;
    

    public fenetre(){
        this.setTitle("Espace Pharmacie");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setLayout(null);
        JPanel c1 = new JPanel();
        idph = new JLabel("ID Pharmacie");
        idph.setPreferredSize(new Dimension(100,100));
        txt_idp = new JTextField();
        txt_idp.setPreferredSize(new Dimension(100,100));
        idmed = new JLabel("ID médicament");
        idmed.setPreferredSize(new Dimension(150,100));
        txt_idmed = new JTextField();
        nom = new JLabel("Nom de médicament");
        nom_txt = new JTextField();
        quant = new JLabel("Quantité");
        quant_txt = new JTextField();
        JButton b1 = new JButton("Envoyer");
        b1.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                i1=txt_idp.getText();
                i2=txt_idmed.getText();
                i3=nom_txt.getText();
                i4=quant_txt.getText();
                ActiveMQConnectionFactory connectionFactory = new
       ActiveMQConnectionFactory("tcp://localhost:61616");
        javax.jms.Connection connection;
        try {
             connection= connectionFactory.createConnection();
             connection.start();
             Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
             Destination destination = session.createQueue("enset.queue");
             MessageProducer producer= session.createProducer(destination);
             producer.setDeliveryMode(DeliveryMode.PERSISTENT);
             ObjectMessage ObjMessage= session.createObjectMessage();
             String[] arr = new String[5];
             arr[0] = i1;
             arr[1] = i2;
             arr[2] = i3;
             arr[3] = i4;
             arr[4] = i5;
            ObjMessage.setObject(arr);
            producer.send(ObjMessage);
            session.close();
            connection.close();
             txt_idp.setText("");
                txt_idmed.setText("");
                quant_txt.setText("");
                nom_txt.setText("");
      
        } catch (JMSException ex) {
        }               
            }
        } );
        JButton b2 = new JButton("Annuler");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt_idp.setText("");
                txt_idmed.setText("");
                quant_txt.setText("");
                nom_txt.setText("");
            }
        });
  
        c1.add(idph);
        c1.add(txt_idp);
        c1.add(idmed);
        c1.add(txt_idmed);
        c1.add(nom);
        c1.add(nom_txt);
        c1.add(quant);
        c1.add(quant_txt);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
    
        p1.add(b1);p1.add(b2);
        c1.add(p2);
        c1.add(p1);
       c1.setLayout(new GridLayout(6,2,2,2));
        c1.setBounds(100, 240, 350, 200);
        titre = new JLabel("Espace Pharmacie");
        titre.setFont(new Font("Bahnschrift SemiCondensed", Font.BOLD, 20));
        titre.setBounds(200, 80, 200, 100);
        this.add(titre);
        this.add(c1);
        titre1 = new JLabel("");
       ImageIcon img = new ImageIcon("1.png");
       JLabel img1= new JLabel(img);
       img1.setPreferredSize(new Dimension(200,200));
       img1.setBounds(0, 0, 100, 58);
       this.add(img1);
       ImageIcon img2 = new ImageIcon("2.png");
       JLabel img21= new JLabel(img2);
       img21.setPreferredSize(new Dimension(100,200));
       img21.setBounds(480,0, 100, 70);
       this.add(img21);
       lb=new JLabel("Reponse: ");
       lb.setBounds(10, 500, 500, 58);
       lb.setFont(new Font(Font.MONOSPACED, Font.BOLD|Font.ITALIC, 15));
       lb.setForeground(Color.black);
       ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
       consumer(connectionFactory);
       
       add(lb);
       
        this.setVisible(true);

        
    }
    void consumer( ActiveMQConnectionFactory coonectionFactory){
                javax.jms.Connection connection;
               
    


         try {
            connection = coonectionFactory.createConnection();
            
             connection.start();
            //create a session
            
            Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //create a topic. topic exist
            Destination destination = session.createQueue("enset.reponse");
            MessageConsumer consumer= session.createConsumer(destination);
            consumer.setMessageListener((Message message) -> {
                if(message instanceof ObjectMessage){
                    ObjectMessage ObjMessage=(ObjectMessage) message ;
                    try {
                        if (ObjMessage.getObject().equals("Votre demande a confirmée"))
                        {
                             lb.setFont(new Font(Font.MONOSPACED, Font.BOLD|Font.ITALIC, 15));
                        lb.setForeground(Color.green);
                        }
                         if (ObjMessage.getObject().equals("Votre demande a refusée car le stocke est insuffisant"))
                        {
                            lb.setFont(new Font(Font.MONOSPACED, Font.BOLD|Font.ITALIC, 12));
                        lb.setForeground(Color.red);
                        }
                        lb.setText("Reponse: "+ObjMessage.getObject()+"");
                    } catch (JMSException ex) {
                        Logger.getLogger(fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                   
                }
            }) ;
            Message message= consumer.receive();
            ObjectMessage ObjMessage= (ObjectMessage) message;
            
            //System.out.println("reception 2: ");
            session.close();
            connection.close();
        } catch (JMSException ex) {
        }
        
    }
    }
    
    

