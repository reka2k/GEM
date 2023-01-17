/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.physique.data;

import java.util.List;
import javax.persistence.Query;
import lml.snir.gem.common.metier.entity.Compteur;
import lml.snir.gem.common.metier.entity.CompteurContrat;
import lml.snir.persistence.jpa.AbstracCrudServiceJPA;

/**
 *
 * @author lml
 */
public class CompteurDataServiceJPAImpl extends AbstracCrudServiceJPA<Compteur> implements CompteurDataService {
    
    public CompteurDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Compteur> getByType(CompteurContrat contrat) throws Exception {
        List<Compteur> compteurs = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT o FROM Compteur o WHERE o.typecontrat = :fcontrat");
            query.setParameter("fcontrat", contrat);
            compteurs = query.getResultList();
        } catch (Exception exception) {
            System.out.println("Erreur Compteur getByType: " + exception.getMessage());
            return null;
        } finally {
            this.close();
        }
        return compteurs;
    }
}
