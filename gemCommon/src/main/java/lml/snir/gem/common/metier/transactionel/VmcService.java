/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.common.metier.transactionel;

import java.util.List;
import lml.snir.persistence.CrudService;
import lml.snir.gem.common.metier.entity.Vmc;

/**
 *
 * @author lml
 */
public interface VmcService extends CrudService<Vmc> {
    public List<Vmc> getById(int id) throws Exception;
}
