package br.com.mark.spark;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@ComponentScan
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
		WebContext webConfig = ctx.getBean(WebContext.class);
		webConfig.startApplication();
		ctx.registerShutdownHook();
	}

	// To resolve ${} in @Value
	@Bean
	public static PropertyPlaceholderConfigurer propertyConfigInDev() {
		PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
		props.setLocations(new Resource[] { new ClassPathResource("application.properties") });
		return props;
	}
}
