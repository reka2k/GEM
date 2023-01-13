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
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ethan
 */
@Entity
@XmlRootElement
public class Vmc implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private int statut;
    @OneToOne
    private CapteurTemperature capteurTemperature;
    @OneToOne
    private CapteurHumidite capteurHumidite;
    private float humidite;
    private float temperature;

    public Vmc() {

    }
    
    public Vmc(int statut,CapteurTemperature capteurTemperature,CapteurHumidite capteurHumidite,float humidite,float temperature) {
        this.capteurTemperature = capteurTemperature;
        this.capteurHumidite = capteurHumidite;
        this.humidite = humidite;
        this.temperature = temperature;

    }

    public void stop() {

    }

    public void petiteVitesse() {

    }

    public void grandeVitesse() {

    }

    /**
     * @return the Statut
     */
    public int getStatut() {
        return statut;
    }

    /**
     * @param statut the Statut to set
     */
    public void setStatut(int statut) {
        this.statut = statut;
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
