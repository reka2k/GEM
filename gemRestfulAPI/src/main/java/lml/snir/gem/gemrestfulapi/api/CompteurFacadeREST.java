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
import lml.snir.gem.common.metier.entity.Compteur;
import lml.snir.gem.common.metier.entity.CompteurContrat;
import lml.snir.gem.common.metier.transactionel.CompteurService;
import lml.snir.gem.gemrestfulapi.transactionel.CompteurServiceImpl;

/**
 *
 * @author david
 */
@Stateless
@Path("/compteur")
public class CompteurFacadeREST extends AbstractFacade<Compteur> {

    private final CompteurService compteurService;
    private EntityManager em;

    public CompteurFacadeREST() {
        super(Compteur.class);
        this.compteurService = new CompteurServiceImpl();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Compteur entity) {

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("insert")
    public Response insert(Compteur compteur) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            this.compteurService.add(compteur);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data inserted successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();

        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Compteur entity) throws Exception {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            entity.setId(id);
            this.compteurService.update(entity);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data edited successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();

        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @DELETE
    @Path("remove/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response remove(@PathParam("id") Long id) throws Exception {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            Compteur compteur = this.compteurService.getById(id);
            this.compteurService.remove(compteur);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data removed successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("/getById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String find(@PathParam("id") Long id) throws Exception {
        try {
            return new Gson().toJson(this.compteurService.getById(id));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    //@Path("getAll")
    public List<Compteur> findAll() {
        return super.findAll();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getAll")
    public Response getAll() {
        try {
            return Response.ok(new Gson().toJson(this.compteurService.getAll())).build();
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getByType/{type}")
    public Response getByType(@PathParam("type") CompteurContrat contrat) throws Exception {
        try {
            return Response.ok(new Gson().toJson(this.compteurService.getByType(contrat))).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
