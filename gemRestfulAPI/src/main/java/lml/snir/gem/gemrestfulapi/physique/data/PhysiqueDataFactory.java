/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi.physique.data;

/**
 *
 * @author david
 */
public class PhysiqueDataFactory {
    private static final String PU = "lml.snir.GEM_GEM_war_1.0PU";

    private static ReleveDataService releveDataService = null;
    public static ReleveDataService getReleveDataService() {
        if(releveDataService == null) releveDataService = new ReleveDataServiceJPAImpl(PU);
        return releveDataService;
    }

    private static SolaireDataService solaireDataService = null;
    public static SolaireDataService getSolaireDataService() {
        if(solaireDataService == null) solaireDataService = new SolaireDataServiceJPAImpl(PU);
        return solaireDataService;
    }
    
    private PhysiqueDataFactory(){
        
    }
    
    private static CapteurHumiditeDataService capteurHumiditeDataService = null;
    
    public synchronized static CapteurHumiditeDataService getCapteurHumiditeDataService(){
        if(capteurHumiditeDataService == null) capteurHumiditeDataService = new CapteurHumiditeDataServiceJPAImpl(PU);
        return capteurHumiditeDataService;
    }
    
    private static CapteurTemperatureDataService capteurTemperatureDataService = null;
    
    public synchronized static CapteurTemperatureDataService getCapteurTemperatureDataService(){
        if(capteurTemperatureDataService == null) capteurTemperatureDataService = new CapteurTemperatureDataServiceJPAImpl(PU);
        return capteurTemperatureDataService;
    }
    
    private static ChauffageDataService chauffageDataService = null;
    public synchronized static ChauffageDataService getChauffageDataService(){
        if(chauffageDataService == null) chauffageDataService = new ChauffageDataServiceJPAImpl(PU);
        return chauffageDataService;
    }
    
    private static SuiviDataService SuiviDataService = null;
    public synchronized static SuiviDataService getSuiviDataService(){
        if(SuiviDataService == null) SuiviDataService = new SuiviDataServiceJPAImpl(PU);
        return SuiviDataService;
    }
    
    private static UserDataService UserDataService = null;
    public synchronized static UserDataService getUserDataService(){
        if(UserDataService == null) UserDataService = new UserDataServiceJPAImpl(PU);
        return UserDataService;
    }
    
    private static CompteurDataService CompteurDataService = null;
    public synchronized static CompteurDataService getCompteurDataService(){
        if(CompteurDataService == null) CompteurDataService = new CompteurDataServiceJPAImpl(PU);
        return CompteurDataService;
    }
    
    private static VmcDataService VmcDataService = null;
    public synchronized static VmcDataService getVmcDataService(){
        if(VmcDataService == null) VmcDataService = new VmcDataServiceJPAImpl(PU);
        return VmcDataService;
    }
}
