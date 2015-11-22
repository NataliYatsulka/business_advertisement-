package com.yatsulka.training;

import static spark.Spark.*;

import java.awt.List;
import java.util.ArrayList;

import spark.Route;

/**
 * Hello world!
 *
 */
public class App {
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
		get("/", (req, res) -> "Hello World!");

		put("/put", (request, response) -> {
			return "Yes";
		});
		ArrayList list = new ArrayList();

		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(new Boolean(true));
		list.add(5);
		list.add(4, "hi");
		list.add(5, "it");
		list.add(8, "is");
		list.add(10, "a");
		list.add(12, "good");
		list.add(13, "day");
		list.add(14, "!");
		list.add(6);
		list.add(343);
		System.out.println(list);
		get("/getall", (request, response) -> {
			return list;
		});
		get("/getbyindex", (request, response) -> {
			return list.get(4);
		});
		get("/getbynumberindex/:numb", (request, response) -> {
			return "list.numb = " + list.get(Integer.valueOf(request.params(":numb"))); // peretvorennja
																						// v
																						// int
		});
		post("/postadd", (request, response) -> {
			return list.add(request.body());
		});

		delete("/delete_by_index/:numb", (request, response) -> {
			return "list.numb = " + list.get(Integer.valueOf(request.params(":numb"))) + "   list1 = " + list.get(4)  ;
			
		});

	}

}