package lml.snir.gem.gemrestfulapi.api;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.gem.common.metier.transactionel.VmcService;
import lml.snir.gem.gemrestfulapi.transactionel.VmcServiceImpl;

/**
 *
 * @author david
 */
@Stateless
@Path("/vmc")
public class VmcFacadeREST extends AbstractFacade<Vmc> {

    private EntityManager em;
    private VmcService vmcService;

    public VmcFacadeREST() {
        super(Vmc.class);
        this.vmcService = new VmcServiceImpl();
    }

    @POST
    @Override
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void create(Vmc entity) {
        super.create(entity);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/insert")
    public Response insert(Vmc vmc) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            this.vmcService.add(vmc);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data inserted successfully")
                    .build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("edit/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response edit(@PathParam("id") Long id, Vmc vmc) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            this.vmcService.update(vmc);
            JsonObject jsonString = factory.createObjectBuilder().add("message", "Data edited successfully")
                    .build();
            return Response.ok(gson.toJson(jsonString), MediaType.APPLICATION_JSON).type("application/json").build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("remove/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response remove(@PathParam("id") Long id) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            this.vmcService.remove(this.vmcService.getById(id));
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
    public Response findVmc(@PathParam("id") Long id) {
        try {
            return Response.ok(new Gson().toJson(this.vmcService.getById(id))).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Override
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Vmc> findAll() {
        return super.findAll();
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/getAll")
    public Response getAll() {
        try {
            return Response.ok(new Gson().toJson(this.vmcService.getAll())).build();
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
