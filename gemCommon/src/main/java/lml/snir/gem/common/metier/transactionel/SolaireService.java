package lml.snir.gem.common.metier.transactionel;

import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.Solaire;
import lml.snir.persistence.CrudService;

/**
 *
 * @author fanou
 */
public interface SolaireService extends CrudService<Solaire>{
    public List<Solaire> getByDay(Date date) throws Exception;
}
