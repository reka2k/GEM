/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.physique.data;
import java.util.List;
import lml.snir.gem.common.metier.entity.Suivi;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.persistence.CrudService;

/**
 *
 * @author lml
 */
public interface SuiviDataService extends CrudService<Suivi>{
    public List<Suivi> getByUser(User user) throws Exception;
    public List<Suivi> getByVmc(Vmc vmc) throws Exception;
    
}
