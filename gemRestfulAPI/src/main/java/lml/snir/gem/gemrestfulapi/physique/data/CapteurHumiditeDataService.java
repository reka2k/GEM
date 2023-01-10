package lml.snir.gem.gemrestfulapi.physique.data;

import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.CapteurHumidite;
import lml.snir.persistence.CrudService;

/**
 *
 * @author david
 */

public interface CapteurHumiditeDataService extends CrudService<CapteurHumidite> {

    public List<CapteurHumidite> getByDate(Date date) throws Exception;

}
