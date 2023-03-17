/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;
import lml.snir.gem.common.metier.transactionel.ReleveService;
import lml.snir.gem.common.metier.entity.Releves;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author david
 */
public class RelevesData implements Serializable {

    private final ReleveService releveSrv = MetierTransactionelFactory.getReleveService();
    private List<Releves> releves;
    private Date date;
    private LineChartModel lineModel;
    private DonutChartModel donutModelHeurePleineCreuse;

    public RelevesData() {
        date = new Date();
        try {
            this.releves = releveSrv.getByDay(date);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public RelevesData(List<Releves> releves, Date date) {
        this.date = date;
        this.releves = releves;
    }

    public void createModelReleve(Date date1) {

        SimpleDateFormat formatter = new SimpleDateFormat("EE MMM d y H:m:s ZZZ");
        // Calendar cal = Calendar.getInstance();
        // cal.set(2022, 12, 3);
        // Date date2 = cal.getTime();
        
        System.out.println(date1);
        
        try {
            this.releves = releveSrv.getByDay(date1);
        } catch (Exception e) {
            System.out.println(e);
        }

        lineModel = new LineChartModel();
        ChartData data = new ChartData();
        List<String> labels = new ArrayList<>();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();

        System.out.println(releves);

        if (releves != null) {

            if (!releves.isEmpty()) {
                releves.forEach(r -> {
                    values.add(r.getPapp());
                    labels.add(formatter.format(r.getDate()));
                });
            } else {
                values.add(12);
                labels.add("x");
            }
        } else {
            values.add(12);
            labels.add("x");
        }

        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Relevés");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        data.addChartDataSet(dataSet);

        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Relevés");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);
        lineModel.setExtender("chartExtender");
        setLineModel(lineModel);

    }

    public void createDonutModelHeurePleineCreuse(Date date) {
        try {
            this.releves = releveSrv.getByDay(date);
        } catch (Exception e) {
            System.out.println(e);
        }

        this.donutModelHeurePleineCreuse = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(300);
        values.add(50);
        values.add(100);

//        releves.forEach(r -> {
//                    values.add(r.getBbrhcjb());
//                });
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Red");
        labels.add("Blue");
        labels.add("Yellow");
        data.setLabels(labels);

        this.donutModelHeurePleineCreuse.setData(data);
    }

    /**
     * @return the releves
     */
    public List<Releves> getReleves() {
        return releves;
    }

    /**
     * @param releves the releves to set
     */
    public void setReleves(List<Releves> releves) {
        this.releves = releves;
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
        this.createModelReleve(this.date);
        return lineModel;
    }

    /**
     * @param lineModel the lineModel to set
     */
    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

}
