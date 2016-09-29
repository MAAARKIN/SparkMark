package br.com.mark.spark;

import static spark.Spark.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mark.spark.model.User;


@Component
public class WebContext {

	@Value("${application.port}")
	private String port;

	public void startApplication() {
		System.out.println(port);
		port(Integer.parseInt(port));

		get("/hello", (req, res) -> "Hello World");
		post("/", (req, res) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				User usuario = mapper.readValue(req.body(), User.class);

				System.out.println(usuario.getName());
				System.out.println(usuario.getAge());

				// chamar o service
				res.status(200);
				res.type("application/json");
			} catch (Exception e) {
				res.status(HttpServletResponse.SC_BAD_REQUEST);
				return "";
			}

			return "";
		});
	}
}
