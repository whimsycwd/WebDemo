package bootstrap;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * jersey 资源的配置。
 * 
 * @author pijunbo
 * @version 2.0.0
 */
public class JerseyResourceConfig extends ResourceConfig {

    public JerseyResourceConfig() {
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
                .property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true)
                .register(JacksonFeature.class)
                .register(MultiPartFeature.class)
                .packages("demo.whimsy.action");
//                .register(SpringLifecycleListener.class);
    }

}
