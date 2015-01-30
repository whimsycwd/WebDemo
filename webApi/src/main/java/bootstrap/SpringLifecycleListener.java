package bootstrap;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.BuilderHelper;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * JAX-RS Provider class for bootstrapping Jersey 2 Spring integration.
 * 
 * @author MarkoAsplund
 * @version 2.0.0
 */
@Deprecated
@Provider
public class SpringLifecycleListener implements ContainerLifecycleListener {
	private ServiceLocator locator;

	@Inject
	public SpringLifecycleListener(ServiceLocator loc) {
		locator = loc;
	}

	@Override
	public void onStartup(Container container) {

		ApplicationContext ctx = null;
		if (container instanceof ServletContainer) {
			ServletContainer sc = (ServletContainer) container;
			ctx = WebApplicationContextUtils.getWebApplicationContext(sc
					.getServletContext());

		} else {
			ctx = new ClassPathXmlApplicationContext(
					new String[] { "applicationContext.xml" });
		}

		if (ctx != null) {
			DynamicConfigurationService dcs = locator
					.getService(DynamicConfigurationService.class);
			DynamicConfiguration c = dcs.createDynamicConfiguration();
			AutowiredInjectResolver r = new AutowiredInjectResolver(ctx);
			c.addActiveDescriptor(BuilderHelper.createConstantDescriptor(r));
			c.addActiveDescriptor(BuilderHelper.createConstantDescriptor(ctx,
					null, ApplicationContext.class));
			c.commit();
		}
	}

	@Override
	public void onReload(Container container) {
	}

	@Override
	public void onShutdown(Container container) {
	}

}