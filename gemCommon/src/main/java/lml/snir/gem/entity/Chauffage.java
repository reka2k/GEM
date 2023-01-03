/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.entity;

/**
 *
 * @author saturne
 */
public class Chauffage {
    
    private boolean Statut;
    private Compteur compteur;
    private boolean heureCreuse;
    private boolean heurePleine;
    
    
public void eteindre(){
    
}
    
public void demarrer(){
    
}

    /**
     * @return the Statut
     */
    public boolean isStatut() {
        return Statut;
    }

    /**
     * @param Statut the Statut to set
     */
    public void setStatut(boolean Statut) {
        this.Statut = Statut;
    }

    /**
     * @return the compteur
     */
    public Compteur getCompteur() {
        return compteur;
    }

    /**
     * @param compteur the compteur to set
     */
    public void setCompteur(Compteur compteur) {
        this.compteur = compteur;
    }

    /**
     * @return the heureCreuse
     */
    public boolean isHeureCreuse() {
        return heureCreuse;
    }

    /**
     * @param heureCreuse the heureCreuse to set
     */
    public void setHeureCreuse(boolean heureCreuse) {
        this.heureCreuse = heureCreuse;
    }

    /**
     * @return the heurePleine
     */
    public boolean isHeurePleine() {
        return heurePleine;
    }

    /**
     * @param heurePleine the heurePleine to set
     */
    public void setHeurePleine(boolean heurePleine) {
        this.heurePleine = heurePleine;
    }
}
