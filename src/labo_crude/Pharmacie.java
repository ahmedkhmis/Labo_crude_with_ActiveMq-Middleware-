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



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pharmacie {
    private static String url= "jdbc:mysql://127.0.0.1/medtransfert";
	private static String user= "root";
	private static String passwd= "";

	private static Connection cn = null;

    public Pharmacie() {
        try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("driver charger...");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("erreur chargement. "+e.getMessage());
			}
	
	
	try { 
		
		cn = DriverManager.getConnection(url, user, passwd);
		System.out.println("connexion etablie");
	} 
	catch ( Exception e ) {
		e.printStackTrace();  
	}
        
        
	  if (cn == null) { try { cn.close(); } catch (SQLException e) {
	  e.printStackTrace(); } }
    }
    
   public String[] RechercheParId(int id) {
		String[] p=new String[2];
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select nom,adress from pharmacie where id=\""+id+"\"");
			if(rs.next()) {
				p[0]=rs.getString("nom"); 
                                p[1]=rs.getString("adress");
				
				
				System.out.println("ID trouver...");
			} else {
				System.out.println("Pas de ID ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()+"Erreur lors de la recherche");
		}
		return p;
	}
   /* public static void main(String[] args) {
        Pharmacie p=new Pharmacie();
        System.out.println("labo_crude.Pharmacie.main()"+ p.RechercheParId(0)[0]);
       
        
    }*/
        
    
}
