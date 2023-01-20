/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.transactionel;

import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.gem.common.metier.transactionel.CapteurTemperatureService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.physique.data.CapteurTemperatureDataService;

/**
 *
 * @author lml
 */
public class CapteurTemperatureServiceImpl implements CapteurTemperatureService{

    private final CapteurTemperatureDataService CapteurTemperatureDataSrv;

    public CapteurTemperatureServiceImpl() {
        this.CapteurTemperatureDataSrv = PhysiqueDataFactory.getCapteurTemperatureDataService();
    }
    
    @Override
    public List<CapteurTemperature> getByDate(Date date) throws Exception {
        return this.CapteurTemperatureDataSrv.getByDate(date);
    }

    @Override
    public CapteurTemperature add(CapteurTemperature t) throws Exception {
        CapteurTemperature capteurTemperature = getById(t.getId());

        if(capteurTemperature != null) throw new Exception("CapteurTemperature déjà existante");

        return this.CapteurTemperatureDataSrv.add(t);
    }

    @Override
    public void remove(CapteurTemperature t) throws Exception {
        this.CapteurTemperatureDataSrv.remove(t);
    }

    @Override
    public void update(CapteurTemperature t) throws Exception {
        this.CapteurTemperatureDataSrv.update(t);
    }

    @Override
    public CapteurTemperature getById(Long id) throws Exception {
        return this.CapteurTemperatureDataSrv.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.CapteurTemperatureDataSrv.getCount();
    }

    @Override
    public List<CapteurTemperature> getAll() throws Exception {
        return this.CapteurTemperatureDataSrv.getAll();
    }

    @Override
    public List<CapteurTemperature> getAll(int i, int i1) throws Exception {
        return this.CapteurTemperatureDataSrv.getAll(i, i1);
    }
    
}
