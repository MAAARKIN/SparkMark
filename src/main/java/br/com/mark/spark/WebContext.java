package br.com.mark.spark;

import static spark.Spark.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.mark.spark.dao.UserDAO;
import br.com.mark.spark.model.User;
import spark.ResponseTransformer;

@Component
public class WebContext {

	public static final String APPLICATION_JSON = "application/json";

	@Autowired
	private UserDAO userDao;

	@Value("${application.port}")
	private String port;

	public void startApplication() {
		System.out.println(port);
		port(Integer.parseInt(port));

		//busca de todos os usuarios
		get("/users", (req, res) -> {
			try {
				res.type(APPLICATION_JSON);
				return userDao.findAll();
			} catch (Exception e) {
				e.printStackTrace(); //usar log4j
				res.status(HttpServletResponse.SC_BAD_REQUEST);
				return "";
			}
		}, jsonConverter());

		//busca de usuario por id
		get("/users/:id", (req, res) -> {
			res.type(APPLICATION_JSON);
			return userDao.findById(Long.valueOf(req.params("id")));
		}, jsonConverter());

		//adicionando novo usuario
		post("/users", (req, res) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				User usuario = mapper.readValue(req.body(), User.class);
				// chamar o service
				userDao.save(usuario);
				res.status(200);
			} catch (Exception e) {
				e.printStackTrace();//usar log4j
				res.status(HttpServletResponse.SC_BAD_REQUEST);
				return "";
			}

			return "";
		});
		
		put("/users/:id", (req, res) -> {
			Long id = Long.valueOf(req.params("id"));
			User userBase = userDao.findById(id);

			if (userBase == null) {
				//lancar exception
				System.out.println("não tem usuario");
			}
			
			try {
				ObjectMapper mapper = new ObjectMapper();
				User usuario = mapper.readValue(req.body(), User.class);
				
				// trecho de código deve ficar no service
				userBase.setAge(usuario.getAge());
				userBase.setName(usuario.getName());
				
				userDao.update(userBase);
				res.status(200);
			} catch (Exception e) {
				e.printStackTrace(); //usar log4j
				res.status(HttpServletResponse.SC_BAD_REQUEST);
				return "";
			}
			return "";
		});
		
		delete("/users/:id", (req, res) -> {
			try {
				Long id = Long.valueOf(req.params("id"));
				User userBase = userDao.findById(id);
				if (userBase == null) {
					//lancar exception
					System.out.println("não tem usuario");
				}
				
				userDao.delete(id);
			} catch (Exception e) {
				e.printStackTrace(); //usar log4j
				res.status(HttpServletResponse.SC_BAD_REQUEST);
				return "";
			}
			return "";
		});
	}

	private ResponseTransformer jsonConverter() {
		return new Gson()::toJson;
	}
}
