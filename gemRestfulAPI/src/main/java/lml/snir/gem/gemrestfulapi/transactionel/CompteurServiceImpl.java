/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.transactionel;

import java.util.List;
import lml.snir.gem.common.metier.entity.Compteur;
import lml.snir.gem.common.metier.entity.CompteurContrat;
import lml.snir.gem.common.metier.transactionel.CompteurService;
import lml.snir.gem.gemrestfulapi.physique.data.CompteurDataService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;

/**
 *
 * @author lml
 */
public class CompteurServiceImpl implements CompteurService {

    private final CompteurDataService CompteurDataSrv;

    public CompteurServiceImpl() {
        this.CompteurDataSrv = PhysiqueDataFactory.getCompteurDataService();
    }

    @Override
    public List<Compteur> getByType(Compteur compteur) throws Exception {
        return this.CompteurDataSrv.getByType(compteur);
    }

    @Override
    public Compteur add(Compteur t) throws Exception {
        Compteur compteur = getById(t.getId());

        if (compteur != null) {
            throw new Exception("Compteur déjà existant");
        }

        return this.CompteurDataSrv.add(t);
    }

    @Override
    public void remove(Compteur t) throws Exception {
        this.CompteurDataSrv.remove(t);
    }

    @Override
    public void update(Compteur t) throws Exception {
        this.CompteurDataSrv.update(t);
    }

    @Override
    public Compteur getById(Long id) throws Exception {
        return this.CompteurDataSrv.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.CompteurDataSrv.getCount();
    }

    @Override
    public List<Compteur> getAll() throws Exception {
        return this.CompteurDataSrv.getAll();
    }

    @Override
    public List<Compteur> getAll(int i, int i1) throws Exception {
        return this.CompteurDataSrv.getAll(i, i1);
    }

}
