package lml.snir.gem.gemrestfulapi.physique.data;


import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.Releves;
import lml.snir.persistence.CrudService;

/**
 *
 * @author fanou
 */
public interface ReleveDataService extends CrudService<Releves>{
    public List<Releves> getByDay(Date date) throws Exception;
    public List<Releves> getByMonth(Date date) throws Exception;
}
