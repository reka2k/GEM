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
import lml.snir.gem.common.metier.entity.Chauffage;
import lml.snir.gem.common.metier.transactionel.ChauffageService;
import lml.snir.gem.gemrestfulapi.transactionel.ChauffageServiceImpl;

/**
 *
 * @author david
 */
@Stateless
@Path("/chauffage")
public class ChauffageFacadeREST extends AbstractFacade<Chauffage>{

    private final ChauffageService chauffageService;
    private EntityManager em;

    public ChauffageFacadeREST() {
        super(Chauffage.class);
        this.chauffageService = new ChauffageServiceImpl();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Chauffage entity) {

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("insert")
    public Response insert(Chauffage chauffage) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            this.chauffageService.add(chauffage);
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
    public Response edit(@PathParam("id") Long id, Chauffage entity) throws Exception {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            entity.setId(id);
            this.chauffageService.update(entity);
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
            Chauffage chauffage = this.chauffageService.getById(id);
            this.chauffageService.remove(chauffage);
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
            return new Gson().toJson(this.chauffageService.getById(id));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    //@Path("getAll")
    public List<Chauffage> findAll() {
        return super.findAll();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getAll")
    public String getAll() {
        try {
            return new Gson().toJson(this.chauffageService.getAll());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
