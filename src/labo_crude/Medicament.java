/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo_crude;

/**
 *
 * @author ben moussa imene
 */
public class Medicament {
    String nom_medicament;
    int boite;
    int quantite;
    double prix;
    int id_med;
public Medicament( int id_med,String nom_medicament,int boite,int quantite,double prix)
{
                this.nom_medicament=nom_medicament;
		this.boite=boite;
		this.quantite=quantite;
                this.prix=prix;
                 this.id_med = id_med;
		
}

    public int getId_med() {
        return id_med;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }

    
    public String getNom_medicament() {
        return nom_medicament;
    }

    public void setNom_medicament(String nom_medicament) {
        this.nom_medicament = nom_medicament;
    }

    public int getBoite() {
        return boite;
    }

    public void setBoite(int boite) {
        this.boite = boite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
}
