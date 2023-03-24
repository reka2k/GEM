/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lml.snir.gem.gemrestfulapi.physique.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import lml.snir.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.gem.common.metier.entity.Releves;

/**
 *
 * @author fanou
 */
class ReleveDataServiceJPAImpl extends AbstracCrudServiceJPA<Releves> implements ReleveDataService {

    public ReleveDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Releves> getByDay(Date date) throws Exception {
        List<Releves> releves = null;
        try {
            this.open();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = sdf.format(date); 
            Date begin = sdf.parse(strDate);
            Date end = (Date) begin.clone();
            end.setHours(23);
            end.setMinutes(59);
            
            Query query = em.createQuery("SELECT o FROM Releves o WHERE o.date BETWEEN :fbegin AND :fend");            
            query.setParameter("fbegin", begin);
            query.setParameter("fend", end);
            releves = query.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            this.close();
        }
        return releves;
    }

    @Override
    public List<Releves> getByMonth(Date date) throws Exception {
        List<Releves> releves = null;
        try {
            this.open();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = sdf.format(date);
            int month =  date.getMonth();
            Date begin = sdf.parse(strDate);
            Date end = (Date) begin.clone();
            end.setMonth(month+1);
           
            
            Query query = em.createQuery("SELECT o FROM Releves o WHERE o.date BETWEEN :fbegin AND :fend");            
            query.setParameter("fbegin", begin);
            query.setParameter("fend", end);
            releves = query.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            this.close();
        }
        return releves;
    
    }
    
}
