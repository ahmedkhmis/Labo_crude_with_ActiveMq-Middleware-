/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_midd;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.*;
import labo_crude.Demandes;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author ASIB
 */
public class QueueCosumer implements Runnable{
   public String[] arry;
    ActiveMQConnectionFactory coonectionFactory = null;
    public QueueCosumer(ActiveMQConnectionFactory coonectionFactory) {
    this.coonectionFactory=coonectionFactory;
    }

    @Override
    public void run() {
        javax.jms.Connection connection;
        try {
            connection = coonectionFactory.createConnection();
            
             connection.start();
            //create a session
             Demandes d= new Demandes();
            
            Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //create a topic. topic exist
            Destination destination = session.createQueue("enset.queue");
            MessageConsumer consumer= session.createConsumer(destination);
            consumer.setMessageListener((Message message) -> {
                if(message instanceof ObjectMessage){
                    try{
                        ObjectMessage ObjMessage=(ObjectMessage) message ;
                         arry = new String[5];
                        arry=(String[]) ObjMessage.getObject();
                        d.CreerRow(arry);
                         
                          // Demandes d=new Demandes(arr);
                        //System.out.println("reception1: "+arr[1]);
                    }   catch (JMSException ex) {
                        Logger.getLogger(QueueCosumer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }) ;
           Message message= consumer.receive();
            ObjectMessage ObjMessage= (ObjectMessage) message;
            arry = new String[5];
                        arry=(String[]) ObjMessage.getObject();
                         d.CreerRow(arry);
            System.out.println("reception 2: "+arry[1]);
            session.close();
            connection.close();
        } catch (JMSException ex) {
        }
 


    }   /*
  public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new
       ActiveMQConnectionFactory("tcp://localhost:61616");
         Thread QueueConsumerThread= new Thread(new QueueCosumer(connectionFactory));
        QueueConsumerThread.start();
        
    }*/

}
