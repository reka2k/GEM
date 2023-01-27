/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.api;

import java.util.Collections;
import java.util.Date;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lml.snir.gem.common.metier.entity.Suivi;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.transactionel.SuiviService;
import lml.snir.gem.gemrestfulapi.transactionel.SuiviServiceImpl;

/**
 *
 * @author david
 */
@Stateless
@Path("/suivi")
public class SuiviFacadeREST extends AbstractFacade<Suivi> {

    // @PersistenceContext(unitName = "lml.snir.GEM_GEM_war_1.0PU")
    private EntityManager em;
    private final SuiviService suiviService;

    public SuiviFacadeREST() {
        super(Suivi.class);
        this.suiviService = new SuiviServiceImpl();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Override
    public void create(Suivi entity) {
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/insert")
    public Response createSuivi(Suivi suivi) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            this.suiviService.add(suivi);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data inserted successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PUT
    @Path("edit/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response edit(@PathParam("id") Long id, Suivi suivi) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            this.suiviService.update(suivi);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data edited successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
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
            this.suiviService.remove(this.suiviService.getById(id));
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data removed successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("getById/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response find(@PathParam("id") Long id) {
        try {
            return Response.ok(new Gson().toJson(this.suiviService.getById(id))).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAll() {
        try {
            return Response.ok(new Gson().toJson(this.suiviService.getAll()), MediaType.APPLICATION_JSON)
                    .type("application/json").build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("getByUser/{user}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getByUser(@PathParam("user") User user) {
        try {
            return Response.ok(new Gson().toJson(this.suiviService.getByUser(user)), MediaType.APPLICATION_JSON)
                    .type("application/json").build();

        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("getByDate/{date}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getByDate(@PathParam("date") Date date) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try {
            return Response.ok(gson.toJson(this.suiviService.getByDate(date)), MediaType.APPLICATION_JSON)
                    .type("application/json").build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Suivi> findAll() {
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
