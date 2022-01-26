/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_midd;

import org.apache.activemq.broker.BrokerService;

/**
 *
 * @author ahmed
 */
public class ActiveMQrun {
      public static void main(String[] args)
   {
        try {
            BrokerService bs = new BrokerService();
            bs.addConnector("tcp://0.0.0.0:61616");
            bs.start();
        } catch (Exception ex) {
        }}
    
}
