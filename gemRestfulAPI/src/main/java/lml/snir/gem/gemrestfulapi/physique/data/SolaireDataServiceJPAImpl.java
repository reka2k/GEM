/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.physique.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import lml.snir.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.gem.common.metier.entity.Solaire;

/**
 *
 * @author david
 */
public class SolaireDataServiceJPAImpl extends AbstracCrudServiceJPA<Solaire> implements SolaireDataService{
    
    public SolaireDataServiceJPAImpl(String PU){
        super(PU);
    }

    @Override
    public List<Solaire> getByDay(Date date) throws Exception {
        List<Solaire> solaire = null;
        try {
            this.open();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = sdf.format(date); 
            Date begin = sdf.parse(strDate);
            Date end = (Date) begin.clone();
            end.setHours(23);
            end.setMinutes(59);
            
            Query query = em.createQuery("SELECT s FROM Solaire s WHERE s.datetime BETWEEN :fbegin AND :fend");            
            query.setParameter("fbegin", begin);
            query.setParameter("fend", end);
            solaire = query.getResultList();
        } catch (NoResultException ex) {
            System.out.println(ex);
            return null;
        } finally {
            this.close();
        }
        return solaire;
    }
    
}
