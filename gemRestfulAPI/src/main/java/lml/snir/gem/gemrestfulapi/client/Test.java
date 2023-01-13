/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.client;

import lml.snir.gem.common.metier.entity.CapteurHumidite;
import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.gem.common.metier.entity.Chauffage;
import lml.snir.gem.common.metier.entity.Compteur;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        CapteurHumidite ch;
        CapteurHumiditeService CapteurHumiditeDataSrv = MetierTransactionelFactory.getCapteurHumiditeService();
        
        CapteurTemperature ct;
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
        
        
        
    }
    
}