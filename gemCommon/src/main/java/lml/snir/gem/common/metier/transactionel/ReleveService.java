package lml.snir.gem.common.metier.transactionel;

import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.Releves;
import lml.snir.persistence.CrudService;

/**
 *
 * @author fanou
 */
public interface ReleveService extends CrudService<Releves>{
    public List<Releves> getByDay(Date date) throws Exception;
}
