package iz.spring.base.config.web;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Spring security initializer.
 * 
 * @author izumi_j
 *
 */
@Order(1)
public final class WebAppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
