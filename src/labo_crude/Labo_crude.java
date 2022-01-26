/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo_crude;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;

/**
 *
 * @author ben moussa imene
 */
public class Labo_crude {

  private static String url= "jdbc:mysql://127.0.0.1/medtransfert";
	private static String user= "root";
	private static String passwd= "";

	private static Connection cn = null;

    public Labo_crude() {
        
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
	  e.printStackTrace(); } }}
          
          
          
          
          //***********************************************//

    /**
     *
     * @param p
     * @return
     */
     public int AjouterMedicament(Medicament p) {
		int a = 0;
		String rq = "insert into laboratoire(id_med,nom_med, boite, quantite,prix) values (?,?,?,?,?)";
		try {
			PreparedStatement ps = cn.prepareStatement(rq);
                        ps.setInt(1, p.getId_med());
			ps.setString(2, p.getNom_medicament());
			ps.setInt(3, p.getBoite());
			ps.setInt(4, p.getQuantite());                 
                        ps.setDouble(5, p.getPrix());

			a = ps.executeUpdate();

			System.out.println("Ajout d'un medicament avec succées");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error " + e.getMessage());
		}
		return a;
	}
	public int Modifier_nom(String nom,String nvnom) {
		// TODO Auto-generated method stub
		int a=0;
		
		String rq = "UPDATE laboratoire set nom_med  = ? where nom_med  = ?";
		try {
			PreparedStatement ps = cn.prepareStatement(rq);
			ps.setString(2,nom);
                        ps.setString(1,nvnom);
			
           
			a=ps.executeUpdate();

			System.out.println("Modification de "+a+" laboratoire avec success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur de modification. " + e.getMessage());
		}
		return a;
	}
public int Modifier_quantite(int quantite,int nvquantite) {
		// TODO Auto-generated method stub
		int a=0;
		
		String rq = "UPDATE laboratoire set quantite  = ? where quantite  = ?";
		try {
			PreparedStatement ps = cn.prepareStatement(rq);
			ps.setInt(2,quantite);
                        ps.setInt(1,nvquantite);
			
           
			a=ps.executeUpdate();

			System.out.println("Modification de "+a+" laboratoire avec success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur de modification. " + e.getMessage());
		}
		return a;
	}

public int Supprimer( String id) {
		int a = 0;
		String rq = "DELETE from laboratoire where nom_med = ?";

		try {
			PreparedStatement ps = cn.prepareStatement(rq);

			ps.setString(1,id);

			a = ps.executeUpdate();

			System.out.println("suppression de " + a + " medicament avec success...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur de suppression. " + e.getMessage());
		}
		return a;
	}
public Medicament RechercheParNom(String id) {
		Medicament p=null;
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select * from laboratoire where nom_med=\""+id+"\"");
			if(rs.next()) {
				p = new Medicament(rs.getInt("id_med"),rs.getString("nom_med"),rs.getInt("boite"), rs.getInt("quantite"), rs.getInt("Prix") );
				
				
				System.out.println("Personne trouver...");
			} else {
				System.out.println("Pas de personne avec ce pseudo.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()+"Erreur lors de la recherche");
		}
		return p;
	}
	public ResultSet getallMed(){
		// selection
		try {Statement st = cn.createStatement();
			ResultSet rs =st.executeQuery("select nom_med from laboratoire");
			
			System.out.println("tous selection avec succées...");
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur du  de selection. "+e.getMessage());
			return null;
		}
	



}
	

    void afficheMed(ResultSet rs, DefaultListModel<Object> model) {
        {
			try {

				ResultSetMetaData rsmd=rs.getMetaData();
				int nbcol=rsmd.getColumnCount();
				
				int nbligne=0;

				while (rs.next())// parcours de lignes
				{
					nbligne++;
                                       
						model.addElement(rs.getObject(1)+"" );
						
				
					
				}
				
				System.out.println("nbre totale = "+nbligne);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }

  
		
}}
	 
	

    
    
        

