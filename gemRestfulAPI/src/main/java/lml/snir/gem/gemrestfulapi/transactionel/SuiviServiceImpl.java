/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.transactionel;

import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.Suivi;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.transactionel.SuiviService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.physique.data.SuiviDataService;

/**
 *
 * @author lml
 */
public class SuiviServiceImpl implements SuiviService{
    
    private SuiviDataService SuiviDataSrv;

    public SuiviServiceImpl() {
        this.SuiviDataSrv = PhysiqueDataFactory.getSuiviDataService();
    }

    @Override
    public List<Suivi> getByUser(User user) throws Exception {
        return this.SuiviDataSrv.getByUser(user);
    }

    @Override
    public List<Suivi> getByDate(Date date) throws Exception {
        return this.SuiviDataSrv.getByDate(date);
    }

    @Override
    public Suivi add(Suivi t) throws Exception {
        List<Suivi> suivis = this.getByDate(t.getDate());

        for (Suivi p : suivis) {
            if (p.equals(t)) {
                throw new Exception("Suivi déjà existante");
            }
        }

        return this.SuiviDataSrv.add(t);
    }

    @Override
    public void remove(Suivi t) throws Exception {
        this.SuiviDataSrv.remove(t);
    }

    @Override
    public void update(Suivi t) throws Exception {
        this.SuiviDataSrv.update(t);
    }

    @Override
    public Suivi getById(Long id) throws Exception {
        return this.SuiviDataSrv.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.SuiviDataSrv.getCount();
    }

    @Override
    public List<Suivi> getAll() throws Exception {
        return this.SuiviDataSrv.getAll();
    }

    @Override
    public List<Suivi> getAll(int i, int i1) throws Exception {
        return this.SuiviDataSrv.getAll(i, i1);
    }
    
}
