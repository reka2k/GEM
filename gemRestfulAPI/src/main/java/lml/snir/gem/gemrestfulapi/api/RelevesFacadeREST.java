/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lml.snir.gem.common.metier.entity.Releves;
import lml.snir.gem.common.metier.transactionel.ReleveService;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author david
 */
@Stateless
@Path("releve")
public class RelevesFacadeREST extends AbstractFacade<Releves> {

    private EntityManager em;
    private ReleveService releveService;

    public RelevesFacadeREST() {
        super(Releves.class);
        this.releveService = MetierTransactionelFactory.getReleveService();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Releves entity) {
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Releves find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Releves> findAll() {
        return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("getByDate")
    public Response getByDate(String jsonDate) throws ParseException {
        JSONObject jsonObject = new JSONObject(jsonDate);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.get("date").toString());
        System.out.println(date);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try {
            List<Releves> releves = this.releveService.getByDay(date);

            if (releves.isEmpty()) {
                return Response.noContent().build();
            }
            return Response.ok(gson.toJson(releves), MediaType.APPLICATION_JSON).type("application/json").build();

        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(406).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
