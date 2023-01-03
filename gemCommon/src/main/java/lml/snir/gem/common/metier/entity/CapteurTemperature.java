/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.common.metier.entity;

import java.util.Date;

/**
 *
 * @author saturne
 */
public class CapteurTemperature {
    
    private char[] mesure;
    private Date date;
    private float temperature;

    public void ExtraireDonneeTrame(){
    
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
