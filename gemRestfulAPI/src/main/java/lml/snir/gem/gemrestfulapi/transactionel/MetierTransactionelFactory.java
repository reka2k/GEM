/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.transactionel;

import lml.snir.gem.common.metier.entity.CapteurTemperature;
import lml.snir.gem.common.metier.transactionel.CapteurHumiditeService;
import lml.snir.gem.common.metier.transactionel.CapteurTemperatureService;
import lml.snir.gem.common.metier.transactionel.ChauffageService;
import lml.snir.gem.common.metier.transactionel.CompteurService;
import lml.snir.gem.common.metier.transactionel.SuiviService;
import lml.snir.gem.common.metier.transactionel.UserService;
import lml.snir.gem.common.metier.transactionel.VmcService;

/**
 *
 * @author lml
 */
public class MetierTransactionelFactory {
    private MetierTransactionelFactory() {}
    
    private static UserService UserSrv = null;
    public synchronized static UserService getUserService(){
        if(UserSrv == null){
            UserSrv = new UserServiceImpl();
        }
        return UserSrv;
    }
    
    private static CapteurHumiditeService CapteurHumiditeSrv = null;
    public synchronized static CapteurHumiditeService getCapteurHumiditeService(){
        if(CapteurHumiditeSrv == null){
            CapteurHumiditeSrv = new CapteurHumiditeServiceImpl();
        }
        return CapteurHumiditeSrv;
    }
    
    private static CapteurTemperatureService CapteurTemperatureSrv = null;
    public synchronized static CapteurTemperatureService getCapteurTemperatureService(){
        if(CapteurTemperatureSrv == null){
            CapteurTemperatureSrv = new CapteurHTemperatureServiceImpl();
        }
        return CapteurTemperatureSrv;
    }
    
    private static ChauffageService ChauffageSrv = null;
    public synchronized static ChauffageService getChauffageService(){
        if(ChauffageSrv == null){
            ChauffageSrv = new ChauffageServiceImpl();
        }
        return ChauffageSrv;
    }
    
    private static CompteurService CompteurSrv = null;
    public synchronized static CompteurService getCompteurService(){
        if(CompteurSrv == null){
            CompteurSrv = new CompteurServiceImpl();
        }
        return CompteurSrv;
    }
    
    private static SuiviService SuiviSrv = null;
    public synchronized static SuiviService getSuiviService(){
        if(SuiviSrv == null){
            SuiviSrv = new SuiviServiceImpl();
        }
        return SuiviSrv;
    }
    
    private static VmcService VmcSrv = null;
    public synchronized static VmcService getVmcService(){
        if(VmcSrv == null){
            VmcSrv = new VmcServiceImpl();
        }
        return VmcSrv;
    }
}
