package br.com.mark.spark;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import br.com.mark.spark.config.WebConfig;

@Configuration
@ComponentScan
public class Application {

	public static void main(String[] args) {
		// ResourceBundle bundle = ResourceBundle.getBundle("environment");
		// System.out.println(bundle.getString("application.port"));

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
		WebConfig webConfig = ctx.getBean(WebConfig.class);
		webConfig.startApplication();

		// rotas para os controlares serão setadas no RouteApplication
		// RouteApplication.setupRoutes();
	}

	// To resolve ${} in @Value
	@Bean
	public static PropertyPlaceholderConfigurer propertyConfigInDev() {
		PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
	    props.setLocations(new Resource[] {new ClassPathResource("application.properties")});
	    return props;
	}
}
