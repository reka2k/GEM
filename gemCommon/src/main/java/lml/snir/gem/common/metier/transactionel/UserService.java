/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.common.metier.transactionel;

import java.util.List;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.persistence.CrudService;

/**
 *
 * @author lml
 */
public interface UserService extends CrudService<User> {
    public List<User> getByLogin(String login) throws Exception;

    public List<User> getByNomPrenom(String nom, String prenom) throws Exception;

    public User getUserByLogin(String login) throws Exception;
}
