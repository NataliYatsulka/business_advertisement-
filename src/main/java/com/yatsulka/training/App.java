package com.yatsulka.training;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import com.google.gson.Gson;

public class App {
	private static UserServise userService = new UserServise();

	public static void main(String[] args) {

		get("/users", (req, res) -> {
			List<User> users = userService.getUsers();
			Gson gson = new Gson();
			String jsonUsers = gson.toJson(users);

			return jsonUsers;

		});

		post("/users", (request, response) -> {
			String json = request.body();
			Gson gson = new Gson();
			User user = gson.fromJson(json, User.class);
			System.out.println(user.toString());
			
			User createdUser = userService.create(user);
			// TODO: serialize to json
			return createdUser;
		});

		get("/users/:id", (request, response) -> {
			Gson gson = new Gson();
			int id = Integer.parseInt(request.params(":id"));
			
			User retrievedUser = userService.getUserById(id);
			String user = gson.toJson(retrievedUser);
			return user;
		});

		delete("/users/:id", (request, response) -> {
			int id = Integer.parseInt(request.params(":id"));
			
			return /* TODO: serialize to json */ userService.deleteUsersById(id);
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