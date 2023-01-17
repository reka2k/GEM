/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import java.lang.reflect.Array;
import java.util.Date;
import lml.snir.gem.common.metier.entity.CapteurHumidite;
import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.gem.common.metier.entity.Chauffage;
import lml.snir.gem.common.metier.entity.Compteur;
import lml.snir.gem.common.metier.entity.CompteurContrat;
import lml.snir.gem.common.metier.entity.Suivi;
import lml.snir.gem.common.metier.entity.User;
import lml.snir.gem.common.metier.entity.Vmc;
import lml.snir.gem.common.metier.transactionel.CapteurHumiditeService;
import lml.snir.gem.common.metier.transactionel.CapteurTemperatureService;
import lml.snir.gem.common.metier.transactionel.ChauffageService;
import lml.snir.gem.common.metier.transactionel.CompteurService;
import lml.snir.gem.common.metier.transactionel.SuiviService;
import lml.snir.gem.common.metier.transactionel.UserService;
import lml.snir.gem.common.metier.transactionel.VmcService;
import lml.snir.gem.gemrestfulapi.physique.data.CapteurHumiditeDataService;
import lml.snir.gem.gemrestfulapi.physique.data.PhysiqueDataFactory;
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
        SuiviService  SuiviDataSrv = MetierTransactionelFactory.getSuiviService();
        
        User u;
        UserService UserDataSrv = MetierTransactionelFactory.getUserService();
        
        Vmc v;
        VmcService VmcDataSrv = MetierTransactionelFactory.getVmcService();
        
        
        
        
        
        u = new User("nom", "prenom", "login", "mdp");
        UserDataSrv.add(u);
        u.setPrenom("david");
        UserDataSrv.update(u);
        System.out.println(UserDataSrv.getByNomPrenom("nom","david"));
        UserDataSrv.remove(u);
        
        v = new Vmc(0, ct, ch, 0, 0);
        VmcDataSrv.add(v);
        v.setId(0);
        VmcDataSrv.update(v);
        System.out.println(VmcDataSrv.getById(0));
        VmcDataSrv.remove(v);
        
        Date date = new Date();
        s = new Suivi(u, date, "action", v);
        SuiviDataSrv.add(s);
        s.setDate(date);
        SuiviDataSrv.update(s);
        System.out.println(SuiviDataSrv.getByDate(date));
        SuiviDataSrv.remove(s);
        
        char[] mesure = new char[5];
        ch = new CapteurHumidite(mesure, date, 0);
        CapteurHumiditeDataSrv.add(ch);
        ch.setHumidite(0);
        CapteurHumiditeDataSrv.update(ch);
        System.out.println(CapteurHumiditeDataSrv.getByDate(date));
        CapteurHumiditeDataSrv.remove(ch);
        
       
        ct = new CapteurTemperature(mesure, date, 0);
        CapteurTemperatureDataSrv.add(ct);
        ct.setMesure(mesure);
        CapteurTemperatureDataSrv.update(ct);
        System.out.println(CapteurTemperatureDataSrv.getByDate(date));
        CapteurTemperatureDataSrv.remove(ct);
        
        c1 = new Compteur(CompteurContrat.Tempo, trame, true, true, 0, 0, 0);
        CompteurDataSrv.add(c1);
        c1.setFactureJour(0);
        CompteurDataSrv.update(c1);
        System.out.println(CompteurDataSrv.getByType(CompteurContrat.Base));
        CompteurDataSrv.remove(c1);
        
        c = new Chauffage(true, c1, true, true);
        ChauffageDataSrv.add(c);
        c.setCompteur(c1);
        ChauffageDataSrv.update(c);
        System.out.println(ChauffageDataSrv.getAll(0, 0));
        ChauffageDataSrv.remove(c);
       
        
        
            }


    
}
