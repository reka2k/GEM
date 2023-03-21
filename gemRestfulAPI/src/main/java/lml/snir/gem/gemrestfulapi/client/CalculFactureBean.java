package lml.snir.gem.gemrestfulapi.client;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import lml.snir.gem.common.metier.entity.Releves;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;
import lml.snir.gem.common.metier.transactionel.ReleveService;

/**
 *
 * @author fanou
 */
@ManagedBean
@ApplicationScoped
public class CalculFactureBean {

    private Date date;

    private String typeContrat = "selectionnez";
    private String result;

    private final ReleveService releveSrv = MetierTransactionelFactory.getReleveService();
    private List<Releves> releves;

    private float HCTB;
    private float HPTB;
    private float HCTW;
    private float HPTW;
    private float HCTR;
    private float HPTR;

    private final double HCTB_prixKWh = 0.0970;
    private final double HPTB_prixKWh = 0.1249;
    private final double HCTW_prixKWh = 0.1140;
    private final double HPTW_prixKWh = 0.1508;
    private final double HCTR_prixKWh = 0.1216;
    private final double HPTR_prixKWh = 0.6712;

    private final double TCFE_taux = 0.00663;
    private final double CSPE_taux = 0.001;
    private final double CTA = 4.02;
    

//    public double calculFacture() {
//        double facture = this.abonnement * 1.055;
//
//        facture += this.HCTB * this.HCTB_taux * 1.2;
//        facture += this.HCTR * this.HCTR_taux * 1.2;
//        facture += this.HCTW * this.HCTW_taux * 1.2;
//        facture += this.HPTB * this.HPTB_taux * 1.2;
//        facture += this.HPTR * this.HPTR_taux * 1.2;
//        facture += this.HPTW * this.HPTW_taux * 1.2;
//
//        int index = this.HCTB + this.HPTB + this.HCTW + this.HPTW + this.HCTR + this.HPTR;
//
//        facture += index * this.TCFE_taux * 1.2;
//        facture += index * this.CSPE_taux * 1.2;
//        facture += this.CTA * 1.055;
//
//        return Math.round(facture * 100.0) / 100.0;
//    }
    
    public String calculFactureJourTempo(Date date) {
        double abo6 = (12.28 / 30); //abonnement 6kVA
        double abo9 = (15.33 / 30); //abonnement 9kVA
        double abo12 = (18.78 / 30); //abonnement 12kVA
        double abo15 = (21.27 / 30); //abonnement 15kVA
        double abo18 = (23.98 / 30); //abonnement 18kVA
        double abo30 = (36.06 / 30); //abonnement 30kVA
        double abo36 = (41.90 / 30); //abonnement 36kVA
        double facture = 0;
        
        facture += this.HCTB * this.HCTB_prixKWh;
        facture += this.HPTB * this.HPTB_prixKWh;
        
        facture += this.HCTW * this.HCTW_prixKWh;
        facture += this.HPTW * this.HPTW_prixKWh;
        
        facture += this.HPTR * this.HPTR_prixKWh;
        facture += this.HCTR * this.HCTR_prixKWh;
        
        switch (this.typeContrat) {
            
            case "tempo6" :
                facture = (facture/1000) + abo6;
                break;
                
            case "tempo9" : 
                facture = (facture/1000) + abo9;
                break;    
                
            case "tempo12" : 
                facture = (facture/1000) + abo12;
                break;
                
            case "tempo15" : 
                facture = (facture/1000) + abo15;
                break;    
                
            case "tempo18" : 
                facture = (facture/1000) + abo18;
                break;
            
            case "tempo30" : 
                facture = (facture/1000) + abo30;
                break;
            
            case "tempo36" : 
                facture = (facture/1000) + abo36;
                break;    
        }
        
        System.out.println("+++++++" + facture);
        
        String factureArrondi = String.format("%.2f", facture);
        System.out.println("*********" + factureArrondi);
        
        this.result = factureArrondi;
        
        if(facture != 0.511){
        } else {
            factureArrondi = " ";
            this.result = factureArrondi;
        }
        
        return factureArrondi;
    }

//    public void calculFactureJour(Date date) {
//        double abo6 = (12.28 / 30); //abonnement 6kVA
//        double abo9 = (15.33 / 30); //abonnement 9kVA
//        double abo12 = (18.78 / 30); //abonnement 12kVA
//        double abo15 = (21.27 / 30); //abonnement 15kVA
//        double abo18 = (23.98 / 30); //abonnement 18kVA
//        double abo30 = (36.06 / 30); //abonnement 30kVA
//        double abo36 = (41.90 / 30); //abonnement 36kVA
//        
//        switch (this.typeContrat) {
//            
//            case "tempo6" :
//                calculFactureJourTempo(date);
//                break;
//                
//            case "tempo9" : 
//                break;    
//                
//            case "tempo12" : 
//                break;
//                
//            case "tempo15" : 
//                break;    
//                
//            case "tempo18" : 
//                break;
//            
//            case "tempo30" : 
//                break;
//            
//            case "tempo36" : 
//                break;    
//        }
//
//        
//    }




    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
        try {
            this.releves = releveSrv.getByDay(date);
            System.out.println("++++++++++++++releves" + this.releves);
            System.out.println("***********date" + this.date);
        } catch (Exception e) {
            System.out.println(e);
        }
            Releves begin = releves.get(0);
            Releves end = releves.get(releves.size() - 1);
            
            this.HCTB = (end.getBbrhcjb() - begin.getBbrhcjb());
            this.HPTB = (end.getBbrhpjb() - begin.getBbrhpjb());
            this.HCTW = (end.getBbrhcjw() - begin.getBbrhcjw());
            this.HPTW = (end.getBbrhpjw() - begin.getBbrhpjw());
            this.HCTR = (end.getBbrhcjr() - begin.getBbrhcjr());
            this.HPTR = (end.getBbrhpjr() - begin.getBbrhpjr());
            System.out.println("++++++++" + this.HCTB +" "+ this.HPTB +" "+ this.HCTW + this.HPTW + this.HCTR + this.HPTR );

    }

    /**
     * @return the typeContrat
     */
    public String getTypeContrat() {
        return typeContrat;
    }

    /**
     * @param typeContrat the typeContrat to set
     */
    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
        System.out.println("++++++++++++++++++" + this.typeContrat + "+++++++++++++++++++++++");
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;

    }

}
