package com.yatsulka.training;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.google.gson.Gson;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class App {
	private static UserServise userService = new UserServise();

	public static void main(String[] args) {

		get("/users", (req, res) -> {
			List<User> users = userService.getUsers();
			Gson gson = new Gson();
			String jsonUsers = gson.toJson(users);

			return jsonUsers;

		});

		get("/users.html", (req, res) -> {
			List<User> users = userService.getUsers();
			
			String html = "<html><head> </head> <body> <h1>ОГОЛОШЕННЯ</h1> ";
			html += "<table border=1 > ";
			for(User u: users){
				html += "<tr><td>" + u.lastName + "</td><td>" + u.firstName + "</td></tr>";
			}
			
			html = html + "</table></body></html>";
			return html;

		});		
		
		get("/users2.html", (req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("users", userService.getUsers());
			return new ModelAndView(model, "users");
		}, new ThymeleafTemplateEngine(TEMPLATE_RESOLVER));

		post("/users", (request, response) -> {
			String json = request.body();
			Gson gson = new Gson();
			User user = gson.fromJson(json, User.class);
			System.out.println(user.toString());

			User createdUser = userService.create(user);
			// serialize to json
			return gson.toJson(createdUser);
		});

		get("/users/:id", (request, response) -> {
			Gson gson = new Gson();
			int id = Integer.parseInt(request.params(":id"));

			User retrievedUser = userService.getUserById(id);
			String user = gson.toJson(retrievedUser);
			return user;
		});

		delete("/users/:id", (request, response) -> {
			Gson gson = new Gson();
			int id = Integer.parseInt(request.params(":id"));

			return /* serialize to json */gson.toJson(userService.deleteUserById(id));
		});

		put("/users/:id", (request, response) -> {
			String json = request.body();
			Gson gson = new Gson();
			User user = gson.fromJson(json, User.class);
			System.out.println(user.toString());
			int id = Integer.parseInt(request.params(":id"));
			user.setId(id);

			// 1. update user
			User updatedUser = userService.update(user);

			// 2. convert result toJson
			String convertedUser = gson.toJson(updatedUser);

			// 3.return json
			return convertedUser;
		});

	}
	
	private static final TemplateResolver TEMPLATE_RESOLVER; 

	static {
		TEMPLATE_RESOLVER = new TemplateResolver();
		TEMPLATE_RESOLVER.setTemplateMode("XHTML");
		TEMPLATE_RESOLVER.setPrefix("templates/");
		TEMPLATE_RESOLVER.setSuffix(".html");
		TEMPLATE_RESOLVER.setCacheTTLMs(3600000L);
		TEMPLATE_RESOLVER.setCacheable(false);

		TEMPLATE_RESOLVER.setResourceResolver(new ClassLoaderResourceResolver());

	}

}