/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.physique.data;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import lml.snir.gem.common.metier.entity.Suivi;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.persistence.jpa.AbstracCrudServiceJPA;

/**
 *
 * @author lml
 */
public class SuiviDataServiceJPAImpl extends AbstracCrudServiceJPA<Suivi> implements SuiviDataService {

    public SuiviDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Suivi> getByUser(User user) throws Exception {
        List<Suivi> suivis = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT o FROM Suvi o WHERE o.user = :user");
            query.setParameter("fuser", user);
            suivis = query.getResultList();
        } catch (Exception exception) {
            System.out.println("Erreur Suivi getByUser: " + exception.getMessage());
            return null;
        } finally {
            this.close();
        }
        return suivis;
    }

    @Override
    public List<Suivi> getByVmc(Vmc vmc) throws Exception {
        List<Suivi> suivis = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT o FROM Suvi o WHERE o.vmc = :vmc");
            query.setParameter("fvmc", vmc);
            suivis = query.getResultList();
        } catch (Exception exception) {
            System.out.println("Erreur Suivi getByVmc: " + exception.getMessage());
            return null;
        } finally {
            this.close();
        }
        return suivis;
    }
}
