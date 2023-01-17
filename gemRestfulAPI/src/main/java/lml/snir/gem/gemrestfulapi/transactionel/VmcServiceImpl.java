/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.transactionel;

import java.util.List;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.gem.common.metier.transactionel.VmcService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.physique.data.VmcDataService;

/**
 *
 * @author lml
 */
public class VmcServiceImpl implements VmcService {

    private final VmcDataService VmcDataSrv;

    public VmcServiceImpl() {
        this.VmcDataSrv = PhysiqueDataFactory.getVmcDataService();
    }

    @Override
    public List<Vmc> getById(int id) throws Exception {
        return this.VmcDataSrv.getById(id);
    }

    @Override
    public Vmc add(Vmc t) throws Exception {
        Vmc vmc = getById(t.getId());

        if (vmc != null) {
            throw new Exception("Vmc déjà existante");
        }

        return this.VmcDataSrv.add(t);
    }

    @Override
    public void remove(Vmc t) throws Exception {
        this.VmcDataSrv.remove(t);
    }

    @Override
    public void update(Vmc t) throws Exception {
        this.VmcDataSrv.update(t);
    }

    @Override
    public Vmc getById(Long id) throws Exception {
        return this.VmcDataSrv.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.VmcDataSrv.getCount();
    }

    @Override
    public List<Vmc> getAll() throws Exception {
        return this.VmcDataSrv.getAll();
    }

    @Override
    public List<Vmc> getAll(int i, int i1) throws Exception {
        return this.VmcDataSrv.getAll(i, i1);
    }

}
