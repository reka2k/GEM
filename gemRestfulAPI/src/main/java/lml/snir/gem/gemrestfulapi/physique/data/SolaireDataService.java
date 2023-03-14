package lml.snir.gem.gemrestfulapi.physique.data;


import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.Solaire;
import lml.snir.persistence.CrudService;

/**
 *
 * @author fanou
 */
public interface SolaireDataService extends CrudService<Solaire>{
    public List<Solaire> getByDay(Date date) throws Exception;
}
