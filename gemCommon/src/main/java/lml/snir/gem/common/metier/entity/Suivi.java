package lml.snir.gem.common.metier.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author David Macario
 */
@Entity
public class Suivi implements Serializable {
    
    @Id
    private long id;
    private User user;
    private Date date;   
    private String action;
    private Vmc vmc;

    public Suivi(){
        
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the vmc
     */
    public Vmc getVmc() {
        return vmc;
    }

    /**
     * @param vmc the vmc to set
     */
    public void setVmc(Vmc vmc) {
        this.vmc = vmc;
    }
    
}
