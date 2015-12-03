package com.yatsulka.training;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class App {
	private static UserServise userService = new UserServise();

	public static void main(String[] args) {
		// TODO: remove
		get("/hello", (req, res) -> "Hello World");

		// TODO: remove
		get("/hello/Nata", (req, res) -> "Hello Nata");

		// TODO: remove
		post("/users", (request, response) -> {
			String input = request.body();
			String[] mas = input.split(",");

			User user = new User(mas[0], mas[1], mas[2]);
			return userService.create(user);
		});
		

		// TODO: remove
		get("/hello/:name/:name1", (request, response) -> {
			return "Hello: " + request.params(":name") + " - - " + request.params(":name1");
		});

		get("/users", (req, res) -> userService.getUsers());

		// TODO: replace with put(/users)
		put("/put", (request, response) -> {
			return "Yes";
		});

		get("/users/:id", (request, response) -> {
			int id = Integer.parseInt(request.params(":id"));
			return userService.getUserById(id);
		});

		delete("/users/:id", (request, response) -> {
			int id = Integer.parseInt(request.params(":id"));
			return userService.deleteUsersById(id);
		});
		
		put("/users/:id", (request, response) -> {
			
			int id = Integer.parseInt(request.params(":id"));
			
			String input = request.body();
			String[] mas = input.split(",");

			User user = new User(id, mas[0], mas[1], mas[2]);
			return userService.update(user);
		});

		// TODO: add post(users)

		// TODO:
		// get by id:
		// GET: /users/{id}

		// delete user by id
		// DELETE: /users/{id}

		// create user
		// POST: /users

		// update user
		// PUT: /users/{id}

		
	}

}