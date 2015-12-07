package com.yatsulka.training;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
    private static UserServise userService = new UserServise();

    public static void main(String[] args) {
        // TODO: remove
        get("/hello", (req, res) -> "Hello World");

        // TODO: remove
        get("/hello/Nata", (req, res) -> "Hello Nata");

        // TODO: remove
        post("/", (request, response) -> {
        	
            return "Hello!";
        });

        // TODO: remove
        get("/hello/:name/:name1", (request, response) -> {
            return "Hello: " + request.params(":name") + " - - " + request.params(":name1");
        });

        get("/users", (req, res) -> {
        	List<User> users = userService.getUsers();
            Gson gson = new Gson();
            String jsonUsers = gson.toJson(users);  

        	return jsonUsers;
        });
     
        // TODO: replace with put(/users)
        post("/users", (request, response) -> {
        	String json = request.body();
            Gson gson = new Gson();
        	User user = gson.fromJson(json, User.class);
        	
        	// TODO: save to DB instead of sysout
        	System.out.println(user.toString());

            return "Yes";
        });


        // TODO: replace with put(/users)
        put("/put", (request, response) -> {
            return "Yes";
        });

        get("/users/:id", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            User user = userService.getUserById(id);

            Gson gson = new Gson();
            String jsonUser = gson.toJson(user);  

            return jsonUser;
        });

        delete("/users/:id", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            return userService.deleteUsersById(id);
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
