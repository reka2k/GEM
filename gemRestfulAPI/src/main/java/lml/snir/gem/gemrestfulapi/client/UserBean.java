/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.transactionel.UserService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;

/**
 *
 * @author lml
 */
@ManagedBean
@RequestScoped
public class UserBean {
    
    private List<User> users;
    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    
    private final UserService userService = MetierTransactionelFactory.getUserService();

    public UserBean() {
        try {
            this.users = PhysiqueDataFactory.getUserDataService().getAll();
        } catch (Exception ex) {

        }
    }

    public void ajouterUser() throws Exception {
        if ((nom.length() > 0) && (prenom.length() > 0) && (login.length() > 0) && (mdp.length() > 0)) {

            User u = new User(nom, prenom, login, mdp);

            userService.add(u);
            try {
                this.users = PhysiqueDataFactory.getUserDataService().getAll();
            } catch (Exception ex) {

            }

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Veuillez remplir tous les champs."));
        }


    }
    
    public void supprimerPersonne(User user) throws Exception {
        try {
            if (user != null) {
                userService.remove(user);
                this.users.remove(user);
            } else {
                throw new Exception("Veuillez séléctionnez un utilisateur");
            }
        } catch (Exception ex) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", ex.getMessage()));
        }

    }
    
    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
