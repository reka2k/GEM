package lml.snir.gem.gemrestfulapi.physique.data;

import lml.snir.gem.common.metier.entity.Chauffage;
import lml.snir.persistence.jpa.AbstracCrudServiceJPA;

/**
 *
 * @author david
 */

public class ChauffageDataServiceJPAImpl extends AbstracCrudServiceJPA<Chauffage> implements ChauffageDataService{
    
    protected ChauffageDataServiceJPAImpl(String PU){
        super(PU);
    }
    
}
