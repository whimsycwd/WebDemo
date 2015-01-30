package bootstrap;

import java.lang.reflect.Type;
import java.util.Map;

import javax.inject.Singleton;

import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 *InjectionResolver class for Spring framework Autowired annotation injection.
 * 
 * @author MarkoAsplund
 * @version 2.0.0
 */
@Singleton
public class AutowiredInjectResolver implements InjectionResolver<Autowired> {

    private ApplicationContext ctx;

    public AutowiredInjectResolver(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object resolve(Injectee injectee, ServiceHandle<?> root) {
        Type t = injectee.getRequiredType();
        if (t instanceof Class) {
            Map<String, ?> beans = ctx.getBeansOfType((Class<?>) t);
            if (!beans.values().isEmpty()) {
                Object o = beans.values().iterator().next();
                return o;
            }
            throw new IllegalStateException(String.format("spring init faild due to bean %s not found",
                                                             ((Class) t).getName()));
        }
        return null;
    }

    @Override
    public boolean isConstructorParameterIndicator() {
        return false;
    }

    @Override
    public boolean isMethodParameterIndicator() {
        return false;
    }

}