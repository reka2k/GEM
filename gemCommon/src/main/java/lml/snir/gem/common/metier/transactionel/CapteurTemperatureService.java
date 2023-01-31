/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.common.metier.transactionel;

import java.util.Date;
import java.util.List;
import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.persistence.CrudService;

/**
 *
 * @author lml
 */
public interface CapteurTemperatureService extends CrudService<CapteurTemperature>{
    public List<CapteurTemperature> getByDate(Date date) throws Exception;
}
