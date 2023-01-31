/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.gem.gemrestfulapi.physique.data;

import java.util.List;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.persistence.jpa.AbstracCrudServiceJPA;
import javax.persistence.Query;
import javax.persistence.NoResultException;

/**
 *
 * @author lml
 */
public class UserDataServiceJPAImpl extends AbstracCrudServiceJPA<User> implements UserDataService {

    public UserDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<User> getByNomPrenom(String nom, String prenom) throws Exception {
        List<User> users = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT o FROM User o WHERE o.nom = :fnom and o.prenom = :fprenom");
            query.setParameter("fnom", nom);
            query.setParameter("fprenom", prenom);
            users = query.getResultList();
        } catch (Exception exception) {
            System.out.println("Erreur User getByNomPrenom: " + exception.getMessage());
            return null;
        } finally {
            this.close();
        }
        return users;
    }

    @Override
    public List<User> getByLogin(String login) throws Exception {
        List<User> users = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT o FROM User o WHERE o.login = :flogin");
            query.setParameter("flogin", login);
            users = query.getResultList();
        } catch (Exception exception) {
            System.out.println("Erreur User getByLogin: " + exception.getMessage());
            return null;
        } finally {
            this.close();
        }
        return users;
    }

    @Override
    public User getUserByLogin(String login) throws Exception {
        User user = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT o FROM User o WHERE o.login = :login");
            query.setParameter("login", login);
            user = (User) query.getSingleResult();
        } catch (Exception exception) {
            System.out.println("Erreur User getUserByLogin: " + exception.getLocalizedMessage());
            return null;
        } finally {
            this.close();
        }
        return user;
    }

}
