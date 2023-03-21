package lml.snir.gem.common.metier.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fanou
 */
@Entity
//@Table(name = "releves")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Releves.findAll", query = "SELECT r FROM Releves r"),
    @NamedQuery(name = "Releves.findById", query = "SELECT r FROM Releves r WHERE r.id = :id"),
    @NamedQuery(name = "Releves.findByBbrhcjb", query = "SELECT r FROM Releves r WHERE r.bbrhcjb = :bbrhcjb"),
    @NamedQuery(name = "Releves.findByBbrhpjb", query = "SELECT r FROM Releves r WHERE r.bbrhpjb = :bbrhpjb"),
    @NamedQuery(name = "Releves.findByBbrhcjw", query = "SELECT r FROM Releves r WHERE r.bbrhcjw = :bbrhcjw"),
    @NamedQuery(name = "Releves.findByBbrhpjw", query = "SELECT r FROM Releves r WHERE r.bbrhpjw = :bbrhpjw"),
    @NamedQuery(name = "Releves.findByBbrhcjr", query = "SELECT r FROM Releves r WHERE r.bbrhcjr = :bbrhcjr"),
    @NamedQuery(name = "Releves.findByBbrhpjr", query = "SELECT r FROM Releves r WHERE r.bbrhpjr = :bbrhpjr"),
    @NamedQuery(name = "Releves.findByPapp", query = "SELECT r FROM Releves r WHERE r.papp = :papp"),
    @NamedQuery(name = "Releves.findByPtec", query = "SELECT r FROM Releves r WHERE r.ptec = :ptec"),
    @NamedQuery(name = "Releves.findByDate", query = "SELECT r FROM Releves r WHERE r.date = :date")})
public class Releves implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "BBRHCJB")
    private int bbrhcjb;
    @Basic(optional = false)
    @Column(name = "BBRHPJB")
    private int bbrhpjb;
    @Basic(optional = false)
    @Column(name = "BBRHCJW")
    private int bbrhcjw;
    @Basic(optional = false)
    @Column(name = "BBRHPJW")
    private int bbrhpjw;
    @Basic(optional = false)
    @Column(name = "BBRHCJR")
    private int bbrhcjr;
    @Basic(optional = false)
    @Column(name = "BBRHPJR")
    private int bbrhpjr;
    @Basic(optional = false)
    @Column(name = "PAPP")
    private int papp;
    @Basic(optional = false)
    @Column(name = "PTEC")
    private String ptec;
    @Basic(optional = false)
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Releves() {
    }

    public Releves(Integer id) {
        this.id = id;
    }

    public Releves(Integer id, int bbrhcjb, int bbrhpjb, int bbrhcjw, int bbrhpjw, int bbrhcjr, int bbrhpjr, int papp, String ptec, Date date) {
        this.id = id;
        this.bbrhcjb = bbrhcjb;
        this.bbrhpjb = bbrhpjb;
        this.bbrhcjw = bbrhcjw;
        this.bbrhpjw = bbrhpjw;
        this.bbrhcjr = bbrhcjr;
        this.bbrhpjr = bbrhpjr;
        this.papp = papp;
        this.ptec = ptec;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBbrhcjb() {
        return bbrhcjb;
    }

    public void setBbrhcjb(int bbrhcjb) {
        this.bbrhcjb = bbrhcjb;
    }

    public int getBbrhpjb() {
        return bbrhpjb;
    }

    public void setBbrhpjb(int bbrhpjb) {
        this.bbrhpjb = bbrhpjb;
    }

    public int getBbrhcjw() {
        return bbrhcjw;
    }

    public void setBbrhcjw(int bbrhcjw) {
        this.bbrhcjw = bbrhcjw;
    }

    public int getBbrhpjw() {
        return bbrhpjw;
    }

    public void setBbrhpjw(int bbrhpjw) {
        this.bbrhpjw = bbrhpjw;
    }

    public int getBbrhcjr() {
        return bbrhcjr;
    }

    public void setBbrhcjr(int bbrhcjr) {
        this.bbrhcjr = bbrhcjr;
    }

    public int getBbrhpjr() {
        return bbrhpjr;
    }

    public void setBbrhpjr(int bbrhpjr) {
        this.bbrhpjr = bbrhpjr;
    }

    public int getPapp() {
        return papp;
    }

    public void setPapp(int papp) {
        this.papp = papp;
    }

    public String getPtec() {
        return ptec;
    }

    public void setPtec(String ptec) {
        this.ptec = ptec;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof Releves)) {
            return false;
        }
        Releves other = (Releves) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        return '"date": ' + sdf.format(date) + ',' +  
//        'bbrhcjb:' + bbrhcjb + ','
//        'bbrhpjb:' + bbrhpjb
//        'bbrhcjw:' + bbrhcjw
//        'bbrhpjw:' + bbrhpjw
//        'bbrhcjr:' + bbrhcjr
//        'bbrhpjr:' + bbrhpjr
//        'papp:' + papp
//        'ptec:' + ptec
//    }
    
}
