/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;

/**
 *
 * @author lml
 */
@ManagedBean(name = "ChartView")
@ApplicationScoped
public class ChartView implements Serializable {

    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createBarModel();
    }
    
    public ChartView() {
        this.createBarModel();
    }


    public void createBarModel() {
        setBarModel(new BarChartModel());
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Consommation quotidienne");

        List<Number> values = new ArrayList<>();
        int b = 4;
        for(int a = 0; a<24; a++){
        values.add(a);
        b= b+5;
        }
        
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgb(0, 79, 250)");
        
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(0, 0, 0)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        for(int i = 0; i<24; i++){
        labels.add(Integer.toString(i));
        }

        data.setLabels(labels);
        this.barModel.setData(data);

//        //Options
//        BarChartOptions options = new BarChartOptions();
//        CartesianScales cScales = new CartesianScales();
//        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
//        linearAxes.setOffset(true);
//        linearAxes.setBeginAtZero(true);
//        CartesianLinearTicks ticks = new CartesianLinearTicks();
//        linearAxes.setTicks(ticks);
//        cScales.addYAxesData(linearAxes);
//        options.setScales(cScales);
//
//        Title title = new Title();
//        title.setDisplay(true);
//        title.setText("Bar Chart");
//        options.setTitle(title);
//
//        Legend legend = new Legend();
//        legend.setDisplay(true);
//        legend.setPosition("top");
//        LegendLabel legendLabels = new LegendLabel();
//        legendLabels.setFontStyle("italic");
//        legendLabels.setFontColor("#2980B9");
//        legendLabels.setFontSize(24);
//        legend.setLabels(legendLabels);
//        options.setLegend(legend);
//
//        // disable animation
//        Animation animation = new Animation();
//        animation.setDuration(0);
//        options.setAnimation(animation);
//
//        barModel.setOptions(options);
    }

    /**
     * @return the barModel
     */
    public BarChartModel getBarModel() {
        this.createBarModel();
        return barModel;
    }

    /**
     * @param barModel the barModel to set
     */
    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
