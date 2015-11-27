package com.yatsulka.training;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
	private static UserServise userService = new UserServise();

	public static void main(String[] args) {
		get("/hello", (req, res) -> "Hello World");
		get("/hello/Nata", (req, res) -> "Hello Nata");
		System.out.println("Hello World!");
		post("/", (request, response) -> {
			return "frf ";
		});

		get("/hello/:name/:name1", (request, response) -> {
			return "Hello: " + request.params(":name") + " - - " + request.params(":name1");
		});
		get("/", (req, res) -> userService.getUsers());

		put("/put", (request, response) -> {
			return "Yes";
		});
		delete("/", (request, response) ->  {
			return ?????????????????????????) ;
		});
		// System.out.println(list);
		// get("/getall", (request, response) -> {
		// return ;
		// });
		// get("/getbyindex", (request, response) -> {
		// return list.get(4);
		// });
		// get("/getbynumberindex/:numb", (request, response) -> {
		// return "list.numb = " +
		// list.get(Integer.valueOf(request.params(":numb"))); // peretvorennja
		// // v
		// // int
		// });
		// post("/postadd", (request, response) -> {
		// return list.add(request.body());
		// });
		//
		// delete("/delete_by_index/:numb", (request, response) -> {

		// return "list.numb = " +
		// list.get(Integer.valueOf(request.params(":numb"))) + " list1 = " +
		// list.get(4);
		//
		// });

	}

}
