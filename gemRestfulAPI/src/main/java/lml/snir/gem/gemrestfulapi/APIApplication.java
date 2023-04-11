/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gem.gemrestfulapi;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import lml.snir.gem.gemrestfulapi.api.CapteurHumiditeFacadeREST;
import lml.snir.gem.gemrestfulapi.api.CapteurTemperatureFacadeREST;
import lml.snir.gem.gemrestfulapi.api.ChauffageFacadeREST;
import lml.snir.gem.gemrestfulapi.api.CompteurFacadeREST;
import lml.snir.gem.gemrestfulapi.api.RelevesFacadeREST;
import lml.snir.gem.gemrestfulapi.api.SolaireFacadeREST;
import lml.snir.gem.gemrestfulapi.api.UserFacadeREST;
import lml.snir.gem.gemrestfulapi.api.VmcFacadeREST;
/**
 *
 * @author david
 */


@ApplicationPath("/api")
public class APIApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add( CapteurHumiditeFacadeREST.class );
        set.add(CapteurTemperatureFacadeREST.class);
        set.add(ChauffageFacadeREST.class);
        set.add(CompteurFacadeREST.class);
        set.add(UserFacadeREST.class);
        set.add(VmcFacadeREST.class);
        set.add(RelevesFacadeREST.class);
        set.add(SolaireFacadeREST.class);

        return set;
    }
}