/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.physique.data;

import java.util.List;
import javax.persistence.Query;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.persistence.jpa.AbstracCrudServiceJPA;

/**
 *
 * @author lml
 */
public class VmcDataServiceJPAImpl extends AbstracCrudServiceJPA<Vmc> implements VmcDataService {
 
    public VmcDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Vmc> getById(int id) throws Exception {
        List<Vmc> vmcs = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT o FROM Vmc o WHERE o.id = :fid");
            query.setParameter("fid", id);
            vmcs = query.getResultList();
        } catch (Exception exception) {
            System.out.println("Erreur Vmc getById: " + exception.getMessage());
            return null;
        } finally {
            this.close();
        }
        return vmcs;
    }
}
