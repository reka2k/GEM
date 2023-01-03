/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.metier.entity;

import lml.snir.gem.common.metier.entity.CompteurContrat;

/**
 *
 * @author lml
 */
public class Compteur {
    //Correspond au type de contrat
    private CompteurContrat typeContrat;
    private String trame;
    private boolean HC; // Utiliser que si le contrat est le bon
    private boolean HP; // Utiliser que si le contrat est le bon
    private float factureJour;
    private float factureSemaine;
    private float factureMois;

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
}
