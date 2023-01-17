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
import lml.snir.gem.common.metier.entity.Suivi;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.gem.common.metier.transactionel.SuiviService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;

/**
 *
 * @author lml
 */
@ManagedBean
@RequestScoped
public class SuiviBean {
    
    private List<Suivi> suivis;
    private User user;
    private Date date;   
    private String action;
    private Vmc vmc;
    
    private final SuiviService suiviService = MetierTransactionelFactory.getSuiviService();

    public SuiviBean() {
        try {
            this.suivis = PhysiqueDataFactory.getSuiviDataService().getAll();
        } catch (Exception ex) {

        }
    }

    /**
     * @return the suivis
     */
    public List<Suivi> getSuivis() {
        return suivis;
    }

    /**
     * @param suivis the suivis to set
     */
    public void setSuivis(List<Suivi> suivis) {
        this.suivis = suivis;
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
