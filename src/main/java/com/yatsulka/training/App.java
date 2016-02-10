package com.yatsulka.training;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			
			StringBuilder html = new StringBuilder("<html><head> </head> <body> <h1>ОГОЛОШЕННЯ</h1> ");
			html.append("<table border=1 > ");

			for(User u: users) {
				html.append("<tr><td>").append(u.lastName).append("</td><td>").append(u.firstName).append("</td></tr>");
			}

			html.append("</table></body></html>");
			return html.toString();
		});
		
		get("/users2.html", (req, res) -> {
			// model
			List<User> users = userService.getUsers();
			Map<String, Object> model = new HashMap<>();
			model.put("myUsers", users);
			
			return new ModelAndView(model, "listUsers");
		}, new ThymeleafTemplateEngine());

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

}