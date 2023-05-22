package lml.snir.gem.gemrestfulapi.client;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import lml.snir.gem.common.metier.entity.Releves;
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

    private String typeContrat = "selectionner";
    private String result;

    private final ReleveService releveSrv = MetierTransactionelFactory.getReleveService();
    private List<Releves> releves;
    private List<Releves> relevesMois;

//****************POUR RÉCUPERER LES VALEUR***********
    private float HCTB;
    private float HPTB;
    private float HCTW;
    private float HPTW;
    private float HCTR;
    private float HPTR;

//****************Pour le contrat TEMPO***************
    private final double HCTB_prixKWh = 0.0970;
    private final double HPTB_prixKWh = 0.1249;
    private final double HCTW_prixKWh = 0.1140;
    private final double HPTW_prixKWh = 0.1508;
    private final double HCTR_prixKWh = 0.1216;
    private final double HPTR_prixKWh = 0.6712;

//****************Pour le contrat HC/HP***************
    private final double HC_prixKWh = 0.1615;
    private final double HP_prixKWh = 0.2228;

//***************Pour le contrat BASE*****************
    private final double BASE_prixKWh = 0.2062;

//****************Pour les taxes**********************
    private final double TCFE_taux = 0.00663;
    private final double CSPE_taux = 0.001;
    private final double CTA = 4.02;

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

            case "tempo6":
                facture = (facture / 1000) + abo6;
                break;
            case "tempo9":
                facture = (facture / 1000) + abo9;
                break;
            case "tempo12":
                facture = (facture / 1000) + abo12;
                break;
            case "tempo15":
                facture = (facture / 1000) + abo15;
                break;
            case "tempo18":
                facture = (facture / 1000) + abo18;
                break;
            case "tempo30":
                facture = (facture / 1000) + abo30;
                break;
            case "tempo36":
                facture = (facture / 1000) + abo36;
                break;
        }
        System.out.println("+++++++" + facture);

        String factureArrondi = String.format("%.2f", facture);
        System.out.println("*********" + factureArrondi);

        this.result = factureArrondi;

        if (facture != 0.511) {
        } else {
            factureArrondi = " ";
            this.result = factureArrondi;
        }

        return factureArrondi;
    }

    public String calculFactureJourHPHC(Date date) {
        double abo6 = (12.35 / 30); //abonnement 6kVA
        double abo9 = (16.03 / 30); //abonnement 9kVA
        double abo12 = (19.34 / 30); //abonnement 12kVA
        double abo15 = (22.50 / 30); //abonnement 15kVA
        double abo18 = (25.58 / 30); //abonnement 18kVA
        double abo24 = (31.69 / 30); //abonnement 24kVA
        double abo30 = (37.68 / 30); //abonnement 30kVA
        double abo36 = (42.42 / 30); //abonnement 36kVA
        double facture = 0;

        facture += this.HCTB * this.HC_prixKWh;
        facture += this.HPTB * this.HP_prixKWh;

        facture += this.HCTW * this.HC_prixKWh;
        facture += this.HPTW * this.HP_prixKWh;

        facture += this.HPTR * this.HP_prixKWh;
        facture += this.HCTR * this.HC_prixKWh;

        switch (this.typeContrat) {

            case "hchp6":
                facture = (facture / 1000) + abo6;
                break;

            case "hchp9":
                facture = (facture / 1000) + abo9;
                break;

            case "hchp12":
                facture = (facture / 1000) + abo12;
                break;

            case "hchp15":
                facture = (facture / 1000) + abo15;
                break;

            case "hchp18":
                facture = (facture / 1000) + abo18;
                break;

            case "hchp30":
                facture = (facture / 1000) + abo30;
                break;

            case "hchp24":
                facture = (facture / 1000) + abo30;
                break;

            case "hchp36":
                facture = (facture / 1000) + abo36;
                break;
        }

        System.out.println("+++++++" + facture);

        String factureArrondi = String.format("%.2f", facture);
        System.out.println("*********" + factureArrondi);

        this.result = factureArrondi;

        if (facture != 0.511) {
        } else {
            factureArrondi = " ";
            this.result = factureArrondi;
        }

        return factureArrondi;
    }

    public String calculFactureJourBase(Date date) {
        double abo3 = (9.13 / 30); //abonnement 3kVA
        double abo6 = (11.93 / 30); //abonnement 6kVA
        double abo9 = (14.86 / 30); //abonnement 9kVA
        double abo12 = (17.88 / 30); //abonnement 12kVA
        double abo15 = (20.85 / 30); //abonnement 15kVA
        double abo18 = (23.67 / 30); //abonnement 18kVA
        double abo24 = (29.82 / 30); //abonnement 24kVA
        double abo30 = (35.83 / 30); //abonnement 30kVA
        double abo36 = (41.71 / 30); //abonnement 36kVA
        double facture = 0;

        facture += this.HCTB * this.BASE_prixKWh;
        facture += this.HPTB * this.BASE_prixKWh;

        facture += this.HCTW * this.BASE_prixKWh;
        facture += this.HPTW * this.BASE_prixKWh;

        facture += this.HPTR * this.BASE_prixKWh;
        facture += this.HCTR * this.BASE_prixKWh;

        switch (this.typeContrat) {

            case "base3":
                facture = (facture / 1000) + abo3;
                break;

            case "base6":
                facture = (facture / 1000) + abo6;
                break;

            case "base9":
                facture = (facture / 1000) + abo9;
                break;

            case "base12":
                facture = (facture / 1000) + abo12;
                break;

            case "base15":
                facture = (facture / 1000) + abo15;
                break;

            case "base18":
                facture = (facture / 1000) + abo18;
                break;

            case "base24":
                facture = (facture / 1000) + abo24;
                break;

            case "base30":
                facture = (facture / 1000) + abo30;
                break;

            case "base36":
                facture = (facture / 1000) + abo36;
                break;
        }

        System.out.println("+++++++" + facture);

        String factureArrondi = String.format("%.2f", facture);
        System.out.println("*********" + factureArrondi);

        this.result = factureArrondi;

        if (facture != 0.511) {
        } else {
            factureArrondi = " ";
            this.result = factureArrondi;
        }

        return factureArrondi;

    }

    public void calculFactureJour(Date date) {

        if (this.typeContrat.contains("tempo")) {
            calculFactureJourTempo(date);
        }

        if (this.typeContrat.contains("hchp")) {
            calculFactureJourHPHC(date);
        }

        if (this.typeContrat.contains("base")) {
            calculFactureJourBase(date);
        }

        if (this.typeContrat.contains("select")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Séléctionnez un contrat."));
        }
    }

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
            System.out.println("++++++++++++++relevesJour" + this.releves);
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
        System.out.println("++++++++" + this.HCTB + " " + this.HPTB + " " + this.HCTW + this.HPTW + this.HCTR + this.HPTR);

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
