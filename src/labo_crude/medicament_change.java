/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo_crude;
    import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author ben moussa imene
 */
public class medicament_change extends AbstractTableModel{



	
	   
		ArrayList<Object[]> data=new ArrayList<Object[]>();
		ResultSetMetaData rsmd=null;
    private Labo_crude Labo_crude;
	    public medicament_change(ResultSet rs)  {
			try {
				rsmd=rs.getMetaData();
				while(rs.next())
				{
					
					Object[]ligne=new Object[rsmd.getColumnCount()];
					for(int i=0;i<ligne.length;i++)
					{
						ligne[i]=rs.getObject(i+1);
					}	
					
						data.add(ligne);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			try {
				return rsmd.getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object  getValueAt(int l, int c) {
			// TODO Auto-generated method stub
			return data.get(l)[c];
		}
		public void addElement(Medicament p) {
			Labo_crude mp = new Labo_crude();
			 int a=mp.AjouterMedicament(p);
			 if(a>0)
			 {System.out.println("ajout avec succes ");}
			 else {System.out.println("erreur d'ajout.");}
			  Object lignes[]= new Object[1];
			  lignes[0]=p.getNom_medicament();
			  //System.out.println("+lignes[0]);
			  data.add(lignes);
			  this.fireTableDataChanged();
			  
			 }
		
		//rennomer
		public void setValueAt(Object val, int l, int c) {
			
			Labo_crude mp= new Labo_crude();
		
			      int a = mp.Modifier_nom(""+data.get(l)[c], ""+ val );
			      System.out.println(val);
			      System.out.println(l);
			      System.out.println(c);
				  if(a>0)
				  { data.get(l)[c]=val;
				  System.out.println("ajout avec succes ");
				   }
				  else{System.out.println("erreur d'ajout.");}
			  
			  
		     data.get(l)[c]=val;
			 this.fireTableDataChanged();
			}
		/*public void supprimer (int ligne) {
			Labo_crude pm= new Labo_crude();
			pm.Supprimer(""+data.get(ligne)[0]);
			data.remove(ligne);
		    this.fireTableDataChanged();//rafraishissement
		
		}*/

		

}

    

