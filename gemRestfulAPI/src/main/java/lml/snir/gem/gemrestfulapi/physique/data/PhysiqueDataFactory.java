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
}
