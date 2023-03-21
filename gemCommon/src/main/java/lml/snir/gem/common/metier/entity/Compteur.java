/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.common.metier.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kelyan Ruas
 */
@Entity
@XmlRootElement
public class Compteur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date_time;
    
    private float baseHourIndex;
    private float fullHourIndex; 
    private float offPeakHourIndex;
    

    private float maxIntensity; //IMAX
    private float instantIntensity; //IINST
    private float subscribeIntensity;
    private float currentTariffOption; //PTEC
    
    
    
       public Compteur(){
        
    }
    
    public Compteur(Date date, float baseHourIndex, float fullHourIndex, float offPeakHourIndex, float maxIntensity, float instantIntensity, float subscribeIntensity, float currentTariffOption){
           this.date_time  = date;
           this.baseHourIndex = baseHourIndex;
           this.fullHourIndex = fullHourIndex;
           this.offPeakHourIndex = offPeakHourIndex;
           this.maxIntensity = maxIntensity;
           this.instantIntensity = instantIntensity;
           this.subscribeIntensity = subscribeIntensity;
           this.currentTariffOption = currentTariffOption;
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

    /**
     * @return the date
     */
    public Date getDate() {
        return date_time;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date_time = date;
    }

    /**
     * @return the baseHourIndex
     */
    public float getBaseHourIndex() {
        return baseHourIndex;
    }

    /**
     * @param baseHourIndex the baseHourIndex to set
     */
    public void setBaseHourIndex(float baseHourIndex) {
        this.baseHourIndex = baseHourIndex;
    }

    /**
     * @return the fullHourIndex
     */
    public float getFullHourIndex() {
        return fullHourIndex;
    }

    /**
     * @param fullHourIndex the fullHourIndex to set
     */
    public void setFullHourIndex(float fullHourIndex) {
        this.fullHourIndex = fullHourIndex;
    }

    /**
     * @return the offPeakHourIndex
     */
    public float getOffPeakHourIndex() {
        return offPeakHourIndex;
    }

    /**
     * @param offPeakHourIndex the offPeakHourIndex to set
     */
    public void setOffPeakHourIndex(float offPeakHourIndex) {
        this.offPeakHourIndex = offPeakHourIndex;
    }

    /**
     * @return the maxIntensity
     */
    public float getMaxIntensity() {
        return maxIntensity;
    }

    /**
     * @param maxIntensity the maxIntensity to set
     */
    public void setMaxIntensity(float maxIntensity) {
        this.maxIntensity = maxIntensity;
    }

    /**
     * @return the instantIntensity
     */
    public float getInstantIntensity() {
        return instantIntensity;
    }

    /**
     * @param instantIntensity the instantIntensity to set
     */
    public void setInstantIntensity(float instantIntensity) {
        this.instantIntensity = instantIntensity;
    }

    /**
     * @return the subscribeIntensity
     */
    public float getSubscribeIntensity() {
        return subscribeIntensity;
    }

    /**
     * @param subscribeIntensity the subscribeIntensity to set
     */
    public void setSubscribeIntensity(float subscribeIntensity) {
        this.subscribeIntensity = subscribeIntensity;
    }

    /**
     * @return the currentTariffOption
     */
    public float getCurrentTariffOption() {
        return currentTariffOption;
    }

    /**
     * @param currentTariffOption the currentTariffOption to set
     */
    public void setCurrentTariffOption(float currentTariffOption) {
        this.currentTariffOption = currentTariffOption;
    }
}
