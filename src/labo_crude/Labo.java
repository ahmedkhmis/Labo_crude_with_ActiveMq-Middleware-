package labo_crude;
import java.awt.Color;
import java.awt.Font;
import static javafx.scene.paint.Color.color;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import projet_midd.QueueCosumer;

/**
 *
 * @author Informatic's queen
 */
public class Labo implements ActionListener {

    private JButton b;
    private JButton b1;
    private JButton b2;
    private JFrame f;
     
    public void interface1(){
    f = new JFrame("ma fenetre");
    f.setTitle("Welecome to MEDTANSFER !");
    f.getContentPane().setBackground(Color.white);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel l2 = new JLabel("BIENVENUE A");
    l2.setBounds(120,10, 400,40); 
    l2.setFont(new Font("Serif", Font.BOLD, 40));
    JLabel l1 = new JLabel("MED TRANSFERT");
    l1.setBounds(100,50, 400,40); 
    l1.setFont(new Font("Serif", Font.BOLD, 40));
    b=new JButton("Consulter medicaments");
    b.setFont(new Font("Times New Roman", Font.BOLD, 20));
    b.setBounds(110,160,300,40);
    b.setBackground(Color.orange);
    b.setOpaque(true);
    b.addActionListener(this);
   /* b1=new JButton("Ajouter medicament");  
    b1.setFont(new Font("Times New Roman", Font.BOLD, 20));
    b1.setBounds(110,180,300,40);
    b1.setBackground(Color.RED);
    b1.setOpaque(true);
    b1.addActionListener(this);*/
    b2=new JButton("Consulter demandes");  
    b2.setFont(new Font("Times New Roman", Font.BOLD, 20));
    b2.setBounds(110,240,300,40);
    b2.setBackground(Color.green);
    b2.setOpaque(true);
    b2.addActionListener(this);
    f.add(l2);
    f.add(l1);
    f.add(b); 
   // f.add(b1); 
    f.add(b2);
    f.setSize(500,500);  
    f.setLayout(null);  
    f.setVisible(true);   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == b ){
     
	   new consulte_med();
       }
          
       /* else if (e.getSource() == b1)
        {
        
	   new consulte_med();
            
        }*/
      else if (e.getSource() == b2){
           
                            
                         ActiveMQConnectionFactory connectionFactory = new
                        ActiveMQConnectionFactory("tcp://localhost:61616");
                           //new QueueCosumer(connectionFactory);
                          Thread QueueConsumerThread= new Thread(new QueueCosumer(connectionFactory));
                         QueueConsumerThread.start();

	 //  new SecondFrame();
      }
         
    }
    public static void main(String[] args)
   {
       
    new Labo().interface1();
   }
}

   

