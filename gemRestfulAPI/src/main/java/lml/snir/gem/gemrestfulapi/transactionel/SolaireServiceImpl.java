package lml.snir.gem.gemrestfulapi.transactionel;

import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.Solaire;
import lml.snir.gem.common.metier.transactionel.SolaireService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.physique.data.SolaireDataService;


/**
 *
 * @author david
 */
public class SolaireServiceImpl implements SolaireService{
    
    SolaireDataService solaireDataSrv = PhysiqueDataFactory.getSolaireDataService();

    @Override
    public List<Solaire> getByDay(Date date) throws Exception {
        return solaireDataSrv.getByDay(date);
    }

    @Override
    public Solaire add(Solaire t) throws Exception {
        return solaireDataSrv.add(t);
    }

    @Override
    public void remove(Solaire t) throws Exception {
        solaireDataSrv.remove(t);
    }

    @Override
    public void update(Solaire t) throws Exception {
        solaireDataSrv.update(t);
    }

    @Override
    public Solaire getById(Long l) throws Exception {
        return solaireDataSrv.getById(l);
    }

    @Override
    public long getCount() throws Exception {
        return solaireDataSrv.getCount();
    }

    @Override
    public List<Solaire> getAll() throws Exception {
        return solaireDataSrv.getAll();
    }

    @Override
    public List<Solaire> getAll(int i, int i1) throws Exception {
        return solaireDataSrv.getAll(i, i1);
    }

    
}
