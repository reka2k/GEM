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
import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.gem.common.metier.transactionel.CapteurTemperatureService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;

/**
 *
 * @author lml
 */

@ManagedBean
@RequestScoped
public class CapteurTemperatureBean {
    
    private List<CapteurTemperature> capteurTemperatures;
    private char[] mesure;
    private Date date;
    private float temperature;
    
    private final CapteurTemperatureService capteurTemperatureService = MetierTransactionelFactory.getCapteurTemperatureService();

    public CapteurTemperatureBean() {
        try {
            this.capteurTemperatures = PhysiqueDataFactory.getCapteurTemperatureDataService().getAll();
        } catch (Exception ex) {

        }
    }

    /**
     * @return the capteurTemperatures
     */
    public List<CapteurTemperature> getCapteurTemperatures() {
        return capteurTemperatures;
    }

    /**
     * @param capteurTemperatures the capteurTemperatures to set
     */
    public void setCapteurTemperatures(List<CapteurTemperature> capteurTemperatures) {
        this.capteurTemperatures = capteurTemperatures;
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
     * @return the temperature
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
