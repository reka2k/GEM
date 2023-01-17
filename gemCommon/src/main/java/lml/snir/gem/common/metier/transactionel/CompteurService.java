/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.common.metier.transactionel;

import java.util.List;
import lml.snir.gem.common.metier.entity.CompteurContrat;
import lml.snir.gem.common.metier.entity.Compteur;
import lml.snir.persistence.CrudService;

/**
 *
 * @author lml
 */
public interface CompteurService extends CrudService<Compteur> {
    public List<Compteur> getByType(Compteur compteur) throws Exception;
}
