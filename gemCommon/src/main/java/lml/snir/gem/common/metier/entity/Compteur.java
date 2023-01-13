/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.common.metier.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kelyan Ruas
 */
@Entity
@XmlRootElement
public class Compteur implements Serializable {
    @Id
    private long id;
    //Correspond au type de contrat
    private CompteurContrat typeContrat;
    private String trame;
    private boolean HC; // Utiliser que si le contrat est le bon
    private boolean HP; // Utiliser que si le contrat est le bon
    private float factureJour;
    private float factureSemaine;
    private float factureMois;

    
    public Compteur(){
        
    }
    
    public Compteur(CompteurContrat typeContrat,String trame,boolean HC,boolean HP,float factureJour,float factureSemaine,float factureMois){
        this.typeContrat = typeContrat;
        this.trame = trame;
        this.HC = HC;
        this.HP = HP;
        this.factureJour = factureJour;
        this.factureSemaine = factureSemaine;
        this.factureMois = factureMois;
        
    }
    
    /**
     * @return the typeContrat
     */
    public CompteurContrat getTypeContrat() {
        return typeContrat;
    }

    /**
     * @param typeContrat the typeContrat to set
     */
    public void setTypeContrat(CompteurContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    /**
     * @return the trame
     */
    public String getTrame() {
        return trame;
    }

    /**
     * @param trame the trame to set
     */
    public void setTrame(String trame) {
        this.trame = trame;
    }

    /**
     * @return the HC
     */
    public boolean isHC() {
        return HC;
    }

    /**
     * @param HC the HC to set
     */
    public void setHC(boolean HC) {
        this.HC = HC;
    }

    /**
     * @return the HP
     */
    public boolean isHP() {
        return HP;
    }

    /**
     * @param HP the HP to set
     */
    public void setHP(boolean HP) {
        this.HP = HP;
    }

    /**
     * @return the factureJour
     */
    public float getFactureJour() {
        return factureJour;
    }

    /**
     * @param factureJour the factureJour to set
     */
    public void setFactureJour(float factureJour) {
        this.factureJour = factureJour;
    }

    /**
     * @return the factureSemaine
     */
    public float getFactureSemaine() {
        return factureSemaine;
    }

    /**
     * @param factureSemaine the factureSemaine to set
     */
    public void setFactureSemaine(float factureSemaine) {
        this.factureSemaine = factureSemaine;
    }

    /**
     * @return the factureMois
     */
    public float getFactureMois() {
        return factureMois;
    }

    /**
     * @param factureMois the factureMois to set
     */
    public void setFactureMois(float factureMois) {
        this.factureMois = factureMois;
    }
    
    public String recuperationTrame(String trame){
        //A completer
        return trame;
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