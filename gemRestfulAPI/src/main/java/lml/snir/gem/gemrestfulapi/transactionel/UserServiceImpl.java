/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.transactionel;

import java.util.List;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.transactionel.UserService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.physique.data.UserDataService;

/**
 *
 * @author lml
 */
public class UserServiceImpl implements UserService {

    private final UserDataService UserDataSrv;

    public UserServiceImpl() {
        this.UserDataSrv = PhysiqueDataFactory.getUserDataService();
    }

    @Override
    public List<User> getByLogin(String login) throws Exception {
        return this.UserDataSrv.getByLogin(login);
    }

    @Override
    public User add(User t) throws Exception {
        User user = UserDataSrv.getById(t.getId());

        if (user != null) {
            throw new Exception("Perssonne déjà existante");
        }
        return this.UserDataSrv.add(t);
    }

    @Override
    public void remove(User t) throws Exception {
        this.UserDataSrv.remove(t);
    }

    @Override
    public void update(User t) throws Exception {
        //test a faire
        this.UserDataSrv.update(t);
    }

    @Override
    public User getById(Long id) throws Exception {
        return this.UserDataSrv.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.UserDataSrv.getCount();
    }

    @Override
    public List<User> getAll() throws Exception {
        return this.UserDataSrv.getAll();
    }

    @Override
    public List<User> getAll(int i, int i1) throws Exception {
        return this.UserDataSrv.getAll(i, i1);
    }

    @Override
    public List<User> getByNomPrenom(String nom, String prenom) throws Exception {
        return this.UserDataSrv.getByNomPrenom(nom, prenom);
    }

}
