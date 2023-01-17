/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import lml.snir.gem.common.metier.entity.Chauffage;
import lml.snir.gem.common.metier.entity.Compteur;
import lml.snir.gem.common.metier.transactionel.ChauffageService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;

/**
 *
 * @author lml
 */
@ManagedBean
@RequestScoped
public class ChauffageBean {
    
    private List<Chauffage> chauffages;
    private boolean statut;
    private Compteur compteur;
    private boolean heureCreuse;
    private boolean heurePleine;
    
    private final ChauffageService chauffageService = MetierTransactionelFactory.getChauffageService();

    public ChauffageBean() {
        try {
            this.chauffages = PhysiqueDataFactory.getChauffageDataService().getAll();
        } catch (Exception ex) {

        }
    }

    /**
     * @return the chauffages
     */
    public List<Chauffage> getChauffages() {
        return chauffages;
    }

    /**
     * @param chauffages the chauffages to set
     */
    public void setChauffages(List<Chauffage> chauffages) {
        this.chauffages = chauffages;
    }


    /**
     * @return the statut
     */
    public boolean isStatut() {
        return statut;
    }

    /**
     * @param statut the statut to set
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
}
