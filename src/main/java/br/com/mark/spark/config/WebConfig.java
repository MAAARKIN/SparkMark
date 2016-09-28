package br.com.mark.spark.config;

import static spark.Spark.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.mark.spark.RouteApplication;

@Component
public class WebConfig {

	@Value("${application.port}")
	private String port;

	public void startApplication() {
		System.out.println(port);
		port(Integer.parseInt(port));
		// rotas para os controlares serão setadas no RouteApplication
		RouteApplication.setupRoutes();
	}
}
