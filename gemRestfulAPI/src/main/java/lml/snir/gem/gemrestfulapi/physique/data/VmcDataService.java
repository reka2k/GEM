/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.physique.data;

import java.util.List;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.persistence.CrudService;

/**
 *
 * @author lml
 */
public interface VmcDataService extends CrudService<Vmc>{
 public List<Vmc> getById(int id) throws Exception;
}
