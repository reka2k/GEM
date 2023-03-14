/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.Solaire;
import lml.snir.gem.common.metier.transactionel.SolaireService;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author lml
 */
public class SolaireData implements Serializable {

    private final SolaireService solaireService = MetierTransactionelFactory.getSolaireService();
    private List<Solaire> solaires;
    private Date date;
    private LineChartModel lineModel;

    public SolaireData() {
        System.out.println(System.getProperty("user.dir"));
        date = new Date();
        try {
            this.solaires = solaireService.getByDay(date);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public SolaireData(List<Solaire> solaires, Date date) {
        this.date = date;
        this.solaires = solaires;
    }

    public void createModelSolaire(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("EE MMM d y H:m:s ZZZ");

        try {
            this.setSolaires(solaireService.getByDay(date));
        } catch (Exception e) {
            System.out.println(e);
        }

        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        LineChartDataSet dataSet2 = new LineChartDataSet();
        LineChartDataSet dataSet3 = new LineChartDataSet();
        LineChartDataSet dataSet4 = new LineChartDataSet();
        List<Object> conso = new ArrayList<>();
        List<Object> capaciteBatterie = new ArrayList<>();
        List<Object> tension = new ArrayList<>();
        List<Object> peak = new ArrayList<>();
        List<String> labels = new ArrayList<>();

//        getSolaires().forEach((Solaire s) -> {
//            conso.add(s.getConso());
//            capaciteBatterie.add(s.getBatteryCapacity());
//            tension.add(s.getBatteryVoltage());
//            peak.add(s.getPVPower());
//            labels.add(formatter.format(s.getDatetime()));
//        });
        conso.add(65);
        conso.add(59);
        conso.add(80);
        conso.add(81);
        conso.add(56);
        conso.add(55);
        conso.add(40);
        
        //Consommation
        dataSet.setData(conso);
        dataSet.setFill(false);
        dataSet.setLabel("Consommation");
        dataSet.setBorderColor("rgb(236, 143, 0)");
        dataSet.setBackgroundColor("rgb(255, 255, 255)");
        data.addChartDataSet(dataSet);
        
        
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");

        // Capacité Batterie
//        dataSet2.setData(capaciteBatterie);
//        dataSet2.setFill(false);
//        dataSet2.setLabel("Capacité Batterie");
//        dataSet2.setBorderColor("rgb(0, 228, 0)");
//        dataSet2.setBackgroundColor("rgb(255, 255, 255)");
//        data.addChartDataSet(dataSet2);
//
//        //Tension Batterie
//        dataSet3.setData(tension);
//        dataSet3.setFill(false);
//        dataSet3.setLabel("Tension Batterie");
//        dataSet3.setBorderColor("rgb(255, 0, 0)");
//        dataSet3.setBackgroundColor("rgb(255, 255, 255)");
//        data.addChartDataSet(dataSet3);
//
//        //Peak Voltage
//        dataSet4.setData(peak);
//        dataSet4.setFill(false);
//        dataSet4.setLabel("Peak Voltage");
//        dataSet4.setBorderColor("rgb(0, 0, 0)");
//        dataSet4.setBackgroundColor("rgb(255, 255, 255)");
//        data.addChartDataSet(dataSet4);
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Solaire");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
        lineModel.setExtender("chartExtender");

    }

    /**
     * @return the releves
     */
    public List<Solaire> getReleves() {
        return getSolaires();
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
     * @return the lineModel
     */
    public LineChartModel getLineModel() {
        this.createModelSolaire(date);
        return lineModel;
    }

    /**
     * @param lineModel the lineModel to set
     */
    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    /**
     * @return the solaires
     */
    public List<Solaire> getSolaires() {
        return solaires;
    }

    /**
     * @param solaires the solaires to set
     */
    public void setSolaires(List<Solaire> solaires) {
        this.solaires = solaires;
    }

}
