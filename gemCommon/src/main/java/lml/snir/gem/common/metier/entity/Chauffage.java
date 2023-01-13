/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.common.metier.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ethan
 */
@Entity
@XmlRootElement
public class Chauffage implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private boolean statut;
    private Compteur compteur;
    private boolean heureCreuse;
    private boolean heurePleine;

    public Chauffage() {

    }
    
     public Chauffage(boolean statut,Compteur compteur,boolean heureCreuse,boolean heurePleine) {
         this.statut = statut;
         this.compteur = compteur;
         this.heureCreuse = heureCreuse;
         this.heurePleine = heurePleine;

    }

    public void eteindre() {

    }

    public void demarrer() {

    }

    /**
     * @return the Statut
     */
    public boolean getStatut() {
        return statut;
    }

    /**
     * @param statut the Statut to set
     */
    public void setStatut(boolean statut) {
        this.statut = statut;
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

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
