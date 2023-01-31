/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
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
import javax.ws.rs.core.Response;
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
    @Consumes({ MediaType.APPLICATION_JSON })
    public void create(User entity) {
        super.create(entity);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("insert")
    public Response insert(User user) {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            List<User> u = this.userService.getByLogin(user.getLogin());
            if (!u.isEmpty())
                throw new Exception("User already exists");
            this.userService.add(user);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data inserted successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();

        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(406, "User already exists").type(MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("edit/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response edit(@PathParam("id") Long id, User user) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            this.userService.update(user);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data edited successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();

        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @DELETE
    @Path("remove/{id}")
    public Response remove(@PathParam("id") Long id) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            User user = userService.getById(id);
            this.userService.remove(user);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data removed successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();

        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("getById/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getById(@PathParam("id") Long id) {
        try {
            return Response.ok(new Gson().toJson(this.userService.getById(id))).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("getAll")
    public Response findAllUsers() {
        try {
            return Response.ok(new Gson().toJson(this.userService.getAll())).build();
        } catch (Exception ex) {
            System.err.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("getByLogin/{login}")
    public Response getByLogin(@PathParam("login") String login) {
        try {
            return Response.ok(new Gson().toJson(this.userService.getByLogin(login))).build();
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/auth/{login}-{password}")
    public Response auth(@PathParam("login") String login, @PathParam("password") String password) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);

        try {
            User user = userService.getUserByLogin(login);
            User hashedPassword = new User();
            hashedPassword.setMdp(password);

            if (hashedPassword.getMdp() == null ? user.getMdp() == null : hashedPassword.getMdp().equals(user.getMdp())) {
                JsonObject jsonString = factory.createObjectBuilder()
                        .add("auth", true)
                        .add("message", "Authentification succesful")
                        .build();
                return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json")
                        .build();
            } else {
                JsonObject jsonString = factory.createObjectBuilder()
                        .add("auth", false)
                        .add("message", "Authentification failed")
                        .build();
                return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json")
                        .build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("getByNomPrenom/{nom}-{prenom}")
    // getByNomPrenom/nom-prenom
    public Response getByNomPrenom(@PathParam("nom") String nom, @PathParam("prenom") String prenom) {
        try {
            return Response.ok(new Gson().toJson(this.userService.getByNomPrenom(nom, prenom))).build();
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
