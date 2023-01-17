/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.transactionel;

import java.util.List;
import lml.snir.gem.common.metier.entity.Chauffage;
import lml.snir.gem.common.metier.transactionel.ChauffageService;
import lml.snir.gem.gemrestfulapi.physique.data.ChauffageDataService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;

/**
 *
 * @author lml
 */
public class ChauffageServiceImpl implements ChauffageService {

    private final ChauffageDataService ChauffageDataSrv;

    public ChauffageServiceImpl() {
        this.ChauffageDataSrv = PhysiqueDataFactory.getChauffageDataService();
    }

    @Override
    public Chauffage add(Chauffage t) throws Exception {
        Chauffage chauffage = getById(t.getId());

        if (chauffage != null) {
            throw new Exception("Chauffage déjà existante");
        }

        return this.ChauffageDataSrv.add(t);
    }

    @Override
    public void remove(Chauffage t) throws Exception {
        this.ChauffageDataSrv.remove(t);
    }

    @Override
    public void update(Chauffage t) throws Exception {
        this.ChauffageDataSrv.update(t);
    }

    @Override
    public Chauffage getById(Long id) throws Exception {
        return this.ChauffageDataSrv.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.ChauffageDataSrv.getCount();
    }

    @Override
    public List<Chauffage> getAll() throws Exception {
        return this.ChauffageDataSrv.getAll();
    }

    @Override
    public List<Chauffage> getAll(int i, int i1) throws Exception {
        return this.ChauffageDataSrv.getAll(i, i1);
    }

}
