package lml.snir.gem.gemrestfulapi.physique.data;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import lml.snir.gem.common.metier.entity.CapteurHumidite;
import lml.snir.persistence.jpa.AbstracCrudServiceJPA;

/**
 *
 * @author david
 */
public class CapteurHumiditeDataServiceJPAImpl extends AbstracCrudServiceJPA<CapteurHumidite> implements CapteurHumiditeDataService{
    
    protected CapteurHumiditeDataServiceJPAImpl(String PU){
        super(PU);
    }

    @Override
    public List<CapteurHumidite> getByDate(Date date) throws Exception {
        List<CapteurHumidite> capteurs;
        try{
            this.open();
            Query query = em.createQuery("SELECT o FROM CapteurHumidite o WHERE o.date = :date");
            query.setParameter("date", date);
            capteurs = query.getResultList();
        }catch(Exception exception){
            System.out.println("Erreur Capteur Humidite getByDate: " + exception.getMessage());
            return null;
        }finally{
            this.close();
        }
        return capteurs;
    }
    
    
}
