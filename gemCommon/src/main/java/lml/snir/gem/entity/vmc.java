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
public class vmc {
    
     private int Statut;
     private CapteurTemperature capteurTemperature;
     private CapteurHumidite capteurHumidite;
     private float humidite;
     private float temperature;
     
     
 public void stop(){
     
 }
 public void petiteVitesse(){
     
 }
 public void grandeVitesse(){
     
 }

    /**
     * @return the Statut
     */
    public int getStatut() {
        return Statut;
    }

    /**
     * @param Statut the Statut to set
     */
    public void setStatut(int Statut) {
        this.Statut = Statut;
    }

    /**
     * @return the capteurTemperature
     */
    public CapteurTemperature getCapteurTemperature() {
        return capteurTemperature;
    }

    /**
     * @param capteurTemperature the capteurTemperature to set
     */
    public void setCapteurTemperature(CapteurTemperature capteurTemperature) {
        this.capteurTemperature = capteurTemperature;
    }

    /**
     * @return the capteurHumidite
     */
    public CapteurHumidite getCapteurHumidite() {
        return capteurHumidite;
    }

    /**
     * @param capteurHumidite the capteurHumidite to set
     */
    public void setCapteurHumidite(CapteurHumidite capteurHumidite) {
        this.capteurHumidite = capteurHumidite;
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