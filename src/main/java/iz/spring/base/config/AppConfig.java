package iz.spring.base.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring application configration.
 *
 * @author izumi_j
 *
 */
@Configuration
@ComponentScan(basePackages = { "iz.spring.base", "iz.spring.sample" })
@Import(JpaConfig.class)
public class AppConfig {

}
