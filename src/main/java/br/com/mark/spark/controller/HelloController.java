package br.com.mark.spark.controller;

import static spark.Spark.*;

import br.com.mark.spark.model.User;
import spark.Request;
import spark.Response;

public class HelloController {

	/**
	 * Get("/hello")
	 * @param req
	 * @param res
	 * @return
	 */
	public String hello(Request req, Response res) {
		User usuario = new User();
		return "teste marcos";
	}
}
