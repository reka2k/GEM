/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.transactionel;

import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.CapteurHumidite;
import lml.snir.gem.common.metier.transactionel.CapteurHumiditeService;
import lml.snir.gem.gemrestfulapi.physique.data.CapteurHumiditeDataService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;

/**
 *
 * @author lml
 */
public class CapteurHumiditeServiceImpl implements CapteurHumiditeService{
    
    private CapteurHumiditeDataService CapteurHumiditeDataSrv;

    public CapteurHumiditeServiceImpl() {
        this.CapteurHumiditeDataSrv = PhysiqueDataFactory.getCapteurHumiditeDataService();
    }

    @Override
    public List<CapteurHumidite> getByDate(Date date) throws Exception {
        return this.CapteurHumiditeDataSrv.getByDate(date);
    }

    @Override
    public CapteurHumidite add(CapteurHumidite t) throws Exception {
        List<CapteurHumidite> capteurHumidites = this.getByDate(t.getDate());

        for (CapteurHumidite p : capteurHumidites) {
            if (p.equals(t)) {
                throw new Exception("CapteurHumidite déjà existante");
            }
        }

        return this.CapteurHumiditeDataSrv.add(t);
    }

    @Override
    public void remove(CapteurHumidite t) throws Exception {
        this.CapteurHumiditeDataSrv.remove(t);
    }

    @Override
    public void update(CapteurHumidite t) throws Exception {
        this.CapteurHumiditeDataSrv.update(t);
    }

    @Override
    public CapteurHumidite getById(Long id) throws Exception {
        return this.CapteurHumiditeDataSrv.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.CapteurHumiditeDataSrv.getCount();
    }

    @Override
    public List<CapteurHumidite> getAll() throws Exception {
        return this.CapteurHumiditeDataSrv.getAll();
    }

    @Override
    public List<CapteurHumidite> getAll(int i, int i1) throws Exception {
        return this.CapteurHumiditeDataSrv.getAll(i, i1);
    }
    
}
