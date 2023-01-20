/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.api;

import com.google.gson.Gson;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.transactionel.UserService;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;

/**
 *
 * @author david
 */
@Stateless
@Path("/user")
public class UserFacadeREST extends AbstractFacade<User> {

    private EntityManager em;
    private final UserService userService;

    public UserFacadeREST() {
        super(User.class);
        this.userService = MetierTransactionelFactory.getUserService();
        
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(User entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, User entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("getAll")
    public String findAllUsers() {
        try {
            return new Gson().toJson(this.userService.getAll());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return null;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
