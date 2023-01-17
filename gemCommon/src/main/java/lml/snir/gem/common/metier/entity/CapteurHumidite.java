/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.common.metier.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saturne
 */
@Entity
@XmlRootElement
public class CapteurHumidite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private char[] mesure;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private float humidite;

    public CapteurHumidite() {

    }
    
      public CapteurHumidite(char[] mesure,Date date,float humidite) {
          this.mesure = mesure;
          this.date = date;
          this.humidite = humidite;

    }

    public void ExtraireDonneeTrame() {

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
