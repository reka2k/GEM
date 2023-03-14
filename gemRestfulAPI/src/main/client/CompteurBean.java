/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import lml.snir.gem.common.metier.entity.Compteur;
import lml.snir.gem.common.metier.entity.CompteurContrat;
import lml.snir.gem.common.metier.transactionel.CompteurService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;

/**
 *
 * @author lml
 */
@ManagedBean
@RequestScoped
public class CompteurBean {
    
    private List<Compteur> compteurs;
    private CompteurContrat typeContrat;//Correspond au type de contrat
    private String trame;
    private boolean HC; // Utiliser que si le contrat convient
    private boolean HP; // Utiliser que si le contrat convient
    private float factureJour;
    private float factureSemaine;
    private float factureMois;
    
    private final CompteurService compteurService = MetierTransactionelFactory.getCompteurService();

    public CompteurBean() {
        try {
            this.compteurs = PhysiqueDataFactory.getCompteurDataService().getAll();
        } catch (Exception ex) {

        }
    }

    /**
     * @return the compteurs
     */
    public List<Compteur> getCompteurs() {
        return compteurs;
    }

    /**
     * @param compteurs the compteurs to set
     */
    public void setCompteurs(List<Compteur> compteurs) {
        this.compteurs = compteurs;
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
}
