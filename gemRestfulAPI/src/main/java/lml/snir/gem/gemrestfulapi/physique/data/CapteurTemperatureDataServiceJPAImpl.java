package lml.snir.gem.gemrestfulapi.physique.data;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.persistence.jpa.AbstracCrudServiceJPA;

/**
 *
 * @author david
 */

public class CapteurTemperatureDataServiceJPAImpl extends AbstracCrudServiceJPA<CapteurTemperature> implements CapteurTemperatureDataService{

    protected CapteurTemperatureDataServiceJPAImpl(String PU){
        super(PU);
    }
    @Override
    public List<CapteurTemperature> getByDate(Date date) throws Exception {
         List<CapteurTemperature> capteurs = null;
        try{
            this.open();
            Query query = em.createQuery("SELECT o FROM CapteurTemperature o WHERE o.date = :date");
            query.setParameter("date", date);
            capteurs = query.getResultList();
        }catch(Exception exception){
            System.out.println("Erreur CapteurTemperature getByDate: " + exception.getMessage());
            return null;
        }finally{
            this.close();
        }
        return capteurs;
    }
    
    
}
