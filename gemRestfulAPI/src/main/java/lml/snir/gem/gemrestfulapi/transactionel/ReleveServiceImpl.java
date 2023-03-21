/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.transactionel;

import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.Releves;
import lml.snir.gem.common.metier.transactionel.ReleveService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.physique.data.ReleveDataService;


/**
 *
 * @author david
 */
public class ReleveServiceImpl implements ReleveService{
    
    ReleveDataService releveDataSrv = PhysiqueDataFactory.getReleveDataService();

    @Override
    public List<Releves> getByDay(Date date) throws Exception {
        return releveDataSrv.getByDay(date);
    }

    @Override
    public Releves add(Releves t) throws Exception {
        return releveDataSrv.add(t);
    }

    @Override
    public void remove(Releves t) throws Exception {
        releveDataSrv.remove(t);
    }

    @Override
    public void update(Releves t) throws Exception {
        releveDataSrv.update(t);
    }

    @Override
    public Releves getById(Long l) throws Exception {
        return releveDataSrv.getById(l);
    }

    @Override
    public long getCount() throws Exception {
        return releveDataSrv.getCount();
    }

    @Override
    public List<Releves> getAll() throws Exception {
       return releveDataSrv.getAll();
    }

    @Override
    public List<Releves> getAll(int i, int i1) throws Exception {
        return releveDataSrv.getAll(i, i1);
    }

    
}
