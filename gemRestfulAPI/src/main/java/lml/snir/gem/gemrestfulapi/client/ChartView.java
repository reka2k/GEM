/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.line.LineChartModel;

/**
 *
 * @author lml
 */
@ManagedBean(name = "ChartView")
@ApplicationScoped
public class ChartView implements Serializable {

    private Date date;
    private SolaireData solaires;
    private RelevesData releves;
    private CalculFactureBean calcul;
    private BarChartModel barModel;
    private LineChartModel linemodel;


    public ChartView() {
        this.date = new Date();
        this.solaires = new SolaireData();
        this.calcul = new CalculFactureBean();
        this.solaires.createModelSolaire(this.date);

        this.releves = new RelevesData();
        // this.releves.createModelReleve(this.date);
        this.linemodel = this.releves.getLineModel();
    }

    public ChartView(Date date) {
        this.date = date;
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
     * @return the solaires
     */
    public SolaireData getSolaires() {
        return solaires;
    }

    /**
     * @param solaires the solaires to set
     */
    public void setSolaires(SolaireData solaires) {
        this.solaires = solaires;
    }

    public void afficherGraph() {

            this.solaires.setDate(this.date);
            this.solaires.createModelSolaire(this.date);
            setLinemodel(this.solaires.getLineModel());
        
    }
    

    public void onDateSelect(SelectEvent<Date> event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
        System.out.println(event.getObject());
    }

    /**
     * @return the barModel
     */
    public BarChartModel getBarModel() {
        this.afficherGraph();
        return barModel;
    }

    /**
     * @param barModel the barModel to set
     */
    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    /**
     * @return the linemodel
     */
    public LineChartModel getLinemodel() {
        return linemodel;
    }

    /**
     * @param linemodel the linemodel to set
     */
    public void setLinemodel(LineChartModel linemodel) {
        this.linemodel = linemodel;
    }

    /**
     * @return the releves
     */
    public RelevesData getReleves() {
        return releves;
    }

    /**
     * @param releves the releves to set
     */
    public void setReleves(RelevesData releves) {
        this.releves = releves;
    }

}
