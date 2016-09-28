package br.com.mark.spark;

import static spark.Spark.get;

import br.com.mark.spark.controller.HelloController;

public class RouteApplication {

	public static void setupRoutes() {
		get("/hello", (req, res) -> new HelloController().hello(req, res));
	}
}
