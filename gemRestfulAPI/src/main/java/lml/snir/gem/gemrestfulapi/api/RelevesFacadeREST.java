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
import javax.json.JsonObject;
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

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response add(String jsonString) {
        Releves releve = new Gson().fromJson(jsonString, Releves.class);
        
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        try {
            
            this.releveService.add(releve);
            JsonObject json = factory.createObjectBuilder().add("message", "Data inserted successfully").build();
            return Response.ok(gson.toJson(json), MediaType.APPLICATION_JSON).type("application/json").build();

        } catch (Exception ex) {
            System.out.println(ex);
            return Response.status(406).type(MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Releves> findAll() {
        return null;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("getByDate")
    public Response getByDate(String jsonDate) throws ParseException {
        JSONObject jsonObject = new JSONObject(jsonDate);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.get("date").toString());
        System.out.println(date);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");

        try {
            List<Releves> releves = this.releveService.getByDay(date);
            String jsonReleves = "";

            if (releves.isEmpty()) {
                return Response.noContent().build();
            }

            for (int i = 0; i < releves.size(); i++) {
                String formattedDate = dateFormatter.format(releves.get(i).getDate());
                if (i == (releves.size() - 1)) {
                    jsonReleves += "{\n"
                            + "\"id\": " + releves.get(i).getId() + ",\n"
                            + "\"bbrhcjb\": " + releves.get(i).getBbrhcjb() + ",\n"
                            + "\"bbrhpjb\": " + releves.get(i).getBbrhpjb() + ",\n"
                            + "\"bbrhcjw\": " + releves.get(i).getBbrhcjw() + ",\n"
                            + "\"bbrhpjw\": " + releves.get(i).getBbrhpjw() + ",\n"
                            + "\"bbrhcjr\": " + releves.get(i).getBbrhcjr() + ",\n"
                            + "\"bbrhpjr\": " + releves.get(i).getBbrhpjr() + ",\n"
                            + "\"papp\": " + releves.get(i).getPapp() + ",\n"
                            + "\"ptec\": \"" + releves.get(i).getPtec() + "\",\n"
                            + "\"date\": \"" + formattedDate + "\"\n }\n";
                } else {
                    jsonReleves += "{\n"
                            + "\"id\": " + releves.get(i).getId() + ",\n"
                            + "\"bbrhcjb\": " + releves.get(i).getBbrhcjb() + ",\n"
                            + "\"bbrhpjb\": " + releves.get(i).getBbrhpjb() + ",\n"
                            + "\"bbrhcjw\": " + releves.get(i).getBbrhcjw() + ",\n"
                            + "\"bbrhpjw\": " + releves.get(i).getBbrhpjw() + ",\n"
                            + "\"bbrhcjr\": " + releves.get(i).getBbrhcjr() + ",\n"
                            + "\"bbrhpjr\": " + releves.get(i).getBbrhpjr() + ",\n"
                            + "\"papp\": " + releves.get(i).getPapp() + ",\n"
                            + "\"ptec\": \"" + releves.get(i).getPtec() + "\",\n"
                            + "\"date\": \"" + formattedDate + "\"\n },\n";
                }
            }

            String jsonArray = "[\n" + jsonReleves + "\n]";
            return Response.ok(jsonArray, MediaType.APPLICATION_JSON).type("application/json").build();

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
