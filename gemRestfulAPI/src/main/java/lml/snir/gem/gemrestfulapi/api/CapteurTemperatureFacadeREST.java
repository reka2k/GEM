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
import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.gem.common.metier.transactionel.CapteurTemperatureService;
import lml.snir.gem.gemrestfulapi.transactionel.CapteurTemperatureServiceImpl;

/**
 *
 * @author david
 */
@Stateless
@Path("/capteurTemperature")
public class CapteurTemperatureFacadeREST extends AbstractFacade<CapteurTemperature> {

    private final CapteurTemperatureService capteurTemperatureService;
    private EntityManager em;

    public CapteurTemperatureFacadeREST() {
        super(CapteurTemperature.class);
        this.capteurTemperatureService = new CapteurTemperatureServiceImpl();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(CapteurTemperature entity) {
        super.create(entity);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("insert")
    public Response insert(CapteurTemperature capteur) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            this.capteurTemperatureService.add(capteur);
            this.capteurTemperatureService.remove(capteur);
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
    public Response edit(@PathParam("id") Long id, CapteurTemperature entity) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            entity.setId(id);
            this.capteurTemperatureService.update(entity);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data edited successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();

        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @DELETE
    @Path("remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Long id) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            CapteurTemperature capteur = this.capteurTemperatureService.getById(id);
            this.capteurTemperatureService.remove(capteur);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data removed successfully").build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();
        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Override
    public List<CapteurTemperature> findAll() {
        return super.findAll();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getAll")
    public String getAll() {
        try {
            return new Gson().toJson(this.capteurTemperatureService.getAll());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    @GET
    @Path("/getById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String find(@PathParam("id") Long id) throws Exception {
        try {
            return new Gson().toJson(this.capteurTemperatureService.getById(id));
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
