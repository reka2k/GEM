/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.util.Date;
import lml.snir.gem.common.metier.entity.CapteurHumidite;
import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.gem.common.metier.entity.Chauffage;
import lml.snir.gem.common.metier.entity.Compteur;
import lml.snir.gem.common.metier.entity.CompteurContrat;
import lml.snir.gem.common.metier.entity.Releves;
import lml.snir.gem.common.metier.entity.Solaire;
import lml.snir.gem.common.metier.entity.Suivi;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.gem.common.metier.transactionel.CapteurHumiditeService;
import lml.snir.gem.common.metier.transactionel.CapteurTemperatureService;
import lml.snir.gem.common.metier.transactionel.ChauffageService;
import lml.snir.gem.common.metier.transactionel.CompteurService;
import lml.snir.gem.common.metier.transactionel.ReleveService;
import lml.snir.gem.common.metier.transactionel.SolaireService;
import lml.snir.gem.common.metier.transactionel.SuiviService;
import lml.snir.gem.common.metier.transactionel.UserService;
import lml.snir.gem.common.metier.transactionel.VmcService;
import lml.snir.gem.gemrestfulapi.transactionel.MetierTransactionelFactory;

/**
 *
 * @author lml
 */
public class Test {

    private static String trame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        CapteurHumidite ch = new CapteurHumidite();
        CapteurHumiditeService CapteurHumiditeDataSrv = MetierTransactionelFactory.getCapteurHumiditeService();

        CapteurTemperature ct = new CapteurTemperature();
        CapteurTemperatureService CapteurTemperatureDataSrv = MetierTransactionelFactory.getCapteurTemperatureService();

        Chauffage c;
        ChauffageService ChauffageDataSrv = MetierTransactionelFactory.getChauffageService();

        Compteur c1;
        CompteurService CompteurDataSrv = MetierTransactionelFactory.getCompteurService();

        Suivi s;
        SuiviService SuiviDataSrv = MetierTransactionelFactory.getSuiviService();

        User u;
        UserService UserDataSrv = MetierTransactionelFactory.getUserService();

        Vmc v;
        VmcService VmcDataSrv = MetierTransactionelFactory.getVmcService();
        
        Releves releve;
        ReleveService releveService = MetierTransactionelFactory.getReleveService();
        
        Solaire solaire;
        SolaireService solaireService = MetierTransactionelFactory.getSolaireService();
        
        

        Date date = new Date();
        
        releve = new Releves();
        releve.setDate(date);
        System.out.println(releve);
        releveService.add(releve);
        
        solaire = new Solaire();
        solaire.setDatetime(date);
        solaireService.add(solaire);

        u = new User("nom", "prenom", "login", "mdp");
        UserDataSrv.add(u);
        u.setPrenom("david");
        u.setId(u.getId());
        UserDataSrv.update(u);
        System.out.println(UserDataSrv.getByNomPrenom("nom", "david"));

        char[] mesure = new char[5];
        ch = new CapteurHumidite(mesure, date, (float) 12.37892);
        CapteurHumiditeDataSrv.add(ch);
        ch.setHumidite((float) 421.48912781);
        CapteurHumiditeDataSrv.update(ch);
        System.out.println(CapteurHumiditeDataSrv.getByDate(date));

        ct = new CapteurTemperature(mesure, date, (float) 312.21241);
        CapteurTemperatureDataSrv.add(ct);
        System.out.println(CapteurTemperatureDataSrv.getByDate(date));

        v = new Vmc(0, ct, ch, 0, 0);
        VmcDataSrv.add(v);
        v.setId(0);
        VmcDataSrv.update(v);
        System.out.println(VmcDataSrv.getById(0));

        s = new Suivi(u, date, "action", v);
        SuiviDataSrv.add(s);
        System.out.println(SuiviDataSrv.getByDate(date));

        c1 = new Compteur(CompteurContrat.TEMPO, trame, true, true, 0, 0, 0);
        CompteurDataSrv.add(c1);
        c1.setFactureJour(0);
        CompteurDataSrv.update(c1);
        System.out.println(CompteurDataSrv.getByType(CompteurContrat.TEMPO));
        CompteurDataSrv.remove(c1);

        c = new Chauffage(true, c1, true, true);
        ChauffageDataSrv.add(c);
        c.setCompteur(c1);
        ChauffageDataSrv.update(c);
        System.out.println(ChauffageDataSrv.getAll());
        
        // Fin test -> on supprimte tout
//        SuiviDataSrv.remove(s);
//        VmcDataSrv.remove(v);
//        UserDataSrv.remove(u);
        CapteurHumiditeDataSrv.remove(ch);
        CapteurTemperatureDataSrv.remove(ct);
//        ChauffageDataSrv.remove(c);
//        CompteurDataSrv.remove(c1);

    }

}
