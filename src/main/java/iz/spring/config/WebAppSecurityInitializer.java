package iz.spring.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(1)
public final class WebAppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
