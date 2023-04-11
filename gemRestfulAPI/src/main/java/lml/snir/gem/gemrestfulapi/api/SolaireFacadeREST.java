/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import lml.snir.gem.common.metier.entity.Solaire;
import lml.snir.gem.common.metier.transactionel.SolaireService;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;
import org.json.JSONObject;

/**
 *
 * @author david
 */
@Stateless
@Path("solaire")
public class SolaireFacadeREST extends AbstractFacade<Solaire> {

    private EntityManager em;
    private SolaireService solaireService;

    public SolaireFacadeREST() {
        super(Solaire.class);
        this.solaireService = MetierTransactionelFactory.getSolaireService();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Solaire entity) {
        super.create(entity);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("getByDate")
    public Response getByDay(String jsonDate) throws ParseException, Exception {
        System.out.println(jsonDate);
        JSONObject jsonObject = new JSONObject(jsonDate);
        System.out.println(jsonObject);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.get("date").toString());
        System.out.println(date);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");

        List<Solaire> solaires = this.solaireService.getByDay(date);
        System.out.println(solaires);
        String jsonReleves = "";

        if (solaires.isEmpty()) {
            return Response.noContent().build();
        }

        for (int i = 0; i < solaires.size(); i++) {
            String formattedDate = dateFormatter.format(solaires.get(i).getDatetime());
            if (i == (solaires.size() - 1)) {
                jsonReleves += "{\n"
                        + "\"id\": " + solaires.get(i).getId() + ",\n"
                        + "\"chargingsourcepriority\": \"" + solaires.get(i).getChargingSourcePriority() + "\",\n"
                        + "\"outputsourcepriority\": \"" + solaires.get(i).getOutputSourcePriority() + "\",\n"
                        + "\"batterycapacity\": " + solaires.get(i).getBatteryCapacity() + ",\n"
                        + "\"batteryvoltage\": " + solaires.get(i).getBatteryVoltage() + ",\n"
                        + "\"conso\": " + solaires.get(i).getConso() + ",\n"
                        + "\"pvpower\": " + solaires.get(i).getPVPower() + ",\n"
                        + "\"date\": \"" + formattedDate + "\"\n }\n";
            } else {
                jsonReleves += "{\n"
                        + "\"id\": " + solaires.get(i).getId() + ",\n"
                        + "\"chargingsourcepriority\": \"" + solaires.get(i).getChargingSourcePriority() + "\",\n"
                        + "\"outputsourcepriority\": \"" + solaires.get(i).getOutputSourcePriority() + "\",\n"
                        + "\"batterycapacity\": " + solaires.get(i).getBatteryCapacity() + ",\n"
                        + "\"batteryvoltage\": " + solaires.get(i).getBatteryVoltage() + ",\n"
                        + "\"conso\": " + solaires.get(i).getConso() + ",\n"
                        + "\"pvpower\": " + solaires.get(i).getPVPower() + ",\n"
                        + "\"date\": \"" + formattedDate + "\"\n },\n";
            }
        }

        String jsonArray = "[\n" + jsonReleves + "\n]";
        return Response.ok(jsonArray, MediaType.APPLICATION_JSON).type("application/json").build();

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
