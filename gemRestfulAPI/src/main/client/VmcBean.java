/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import lml.snir.gem.common.metier.entity.CapteurHumidite;
import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.gem.common.metier.transactionel.VmcService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;

/**
 *
 * @author lml
 */
@ManagedBean
@RequestScoped
public class VmcBean {
    
    private List<Vmc> vmcs;
    private int statut;
    private CapteurTemperature capteurTemperature;
    private CapteurHumidite capteurHumidite;
    private float humidite;
    private float temperature;
    
    private final VmcService vmcService = MetierTransactionelFactory.getVmcService();

    public VmcBean() {
        try {
            this.vmcs = PhysiqueDataFactory.getVmcDataService().getAll();
        } catch (Exception ex) {

        }
    }

    /**
     * @return the vmcs
     */
    public List<Vmc> getVmcs() {
        return vmcs;
    }

    /**
     * @param vmcs the vmcs to set
     */
    public void setVmcs(List<Vmc> vmcs) {
        this.vmcs = vmcs;
    }

    /**
     * @return the statut
     */
    public int getStatut() {
        return statut;
    }

    /**
     * @param statut the statut to set
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
    
    
}
