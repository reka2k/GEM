package lml.snir.gem.common.metier.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fanou
 */
@Entity
//@Table(name = "solaire")
@XmlRootElement
public class Solaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Conso")
    private int conso;
    @Basic(optional = false)
    @Column(name = "BatteryCapacity")
    private int batteryCapacity;
    @Basic(optional = false)
    @Column(name = "BatteryVoltage")
    private float batteryVoltage;
    @Basic(optional = false)
    @Column(name = "PVPower")
    private int pVPower;
    @Basic(optional = false)
    @Column(name = "OutputSourcePriority")
    private String outputSourcePriority;
    @Basic(optional = false)
    @Column(name = "ChargingSourcePriority")
    private String chargingSourcePriority;
    @Basic(optional = false)
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;


    public Solaire() {
    }

    public Solaire(Integer id) {
        this.id = id;
    }

    public Solaire(Integer id, int conso, int batteryCapacity, float batteryVoltage, int pVPower, String outputSourcePriority, String chargingSourcePriority, Date datetime) {
        this.id = id;
        this.conso = conso;
        this.batteryCapacity = batteryCapacity;
        this.batteryVoltage = batteryVoltage;
        this.pVPower = pVPower;
        this.outputSourcePriority = outputSourcePriority;
        this.chargingSourcePriority = chargingSourcePriority;
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getConso() {
        return conso;
    }

    public void setConso(int conso) {
        this.conso = conso;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public float getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(float batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public int getPVPower() {
        return pVPower;
    }

    public void setPVPower(int pVPower) {
        this.pVPower = pVPower;
    }

    public String getOutputSourcePriority() {
        return outputSourcePriority;
    }

    public void setOutputSourcePriority(String outputSourcePriority) {
        this.outputSourcePriority = outputSourcePriority;
    }

    public String getChargingSourcePriority() {
        return chargingSourcePriority;
    }

    public void setChargingSourcePriority(String chargingSourcePriority) {
        this.chargingSourcePriority = chargingSourcePriority;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solaire)) {
            return false;
        }
        Solaire other = (Solaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Solaire[ id=" + id + " ]";
    }
    
}
