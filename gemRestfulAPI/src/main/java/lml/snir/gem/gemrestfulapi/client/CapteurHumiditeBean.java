/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import lml.snir.gem.common.metier.entity.CapteurHumidite;
import lml.snir.gem.common.metier.transactionel.CapteurHumiditeService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;

/**
 *
 * @author lml
 */

@ManagedBean
@RequestScoped
public class CapteurHumiditeBean {
    
    private List<CapteurHumidite> capteurHumidites;
    private char[] mesure;
    private Date date;
    private float humidite;
    
    private final CapteurHumiditeService capteurHumiditeService = MetierTransactionelFactory.getCapteurHumiditeService();

    public CapteurHumiditeBean() {
        try {
            this.capteurHumidites = PhysiqueDataFactory.getCapteurHumiditeDataService().getAll();
        } catch (Exception ex) {

        }
    }

    /**
     * @return the capteurHumidites
     */
    public List<CapteurHumidite> getCapteurHumidites() {
        return capteurHumidites;
    }

    /**
     * @param capteurHumidites the capteurHumidites to set
     */
    public void setCapteurHumidites(List<CapteurHumidite> capteurHumidites) {
        this.capteurHumidites = capteurHumidites;
    }

    /**
     * @return the mesure
     */
    public char[] getMesure() {
        return mesure;
    }

    /**
     * @param mesure the mesure to set
     */
    public void setMesure(char[] mesure) {
        this.mesure = mesure;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the humidite
     */
    public float getHumidite() {
        return humidite;
    }

    /**
     * @param humidite the humidite to set
     */
    public void setHumidite(float humidite) {
        this.humidite = humidite;
    }
}
