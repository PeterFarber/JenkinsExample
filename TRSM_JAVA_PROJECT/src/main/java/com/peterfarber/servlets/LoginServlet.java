package com.peterfarber.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterfarber.dao.UserDao;
import com.peterfarber.pojo.User;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Setup the response
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200/*");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String session = request.getParameter("JSESSIONID");

        //Setup PrintWriter
        PrintWriter pr = response.getWriter();

        //Create a JSONObject
        JSONObject obj = new JSONObject();


        // Get the request session (Do not create a new one if it doesn't exist)!
        //HttpSession session = request.getSession(false);
        if (session.isEmpty()) {
            //Create a DAO to access the users table.
            UserDao userDAO = new UserDao();
            //Get the user based on the username sent upon request!
            User user = userDAO.retrieveByString(username);

            //Check if the user is not null (if it is that means it doesn't exist in database)
            if(user != null){
                //If the user exists check if the password is correct.
                if(user.getPassword().compareTo(password) == 0){
                    ObjectMapper objectMapper = new ObjectMapper();
                    obj.put("user", objectMapper.writeValueAsString(user));
                    //Start a new session!
                    HttpSession sess = request.getSession();
                    obj.put("Session_ID", sess.getId().toString());

                }else{
                    obj.put("InvalidPassword", true);
                }
            }else{
                //Modify UserExists in json object!
                obj.put("UserNotFound", true);
            }
        }else{
            obj.put("Session_ID", session);
        }

        pr.write(obj.toJSONString());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Set response content type
//        response.setContentType("application/json");
//
//        //Login Servlet
//        PrintWriter pr = response.getWriter();
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.setAttribute("count", 1+ (int)session.getAttribute("count"));
//            System.out.println("Old Session: " + session.getAttribute("count"));
//        } else {
//            session = request.getSession();
//            System.out.println("New Session: " + session.getAttribute("count"));
//            session.setAttribute("count", 0);
//        }
//
//        response.addHeader("Access-Control-Allow-Origin","http://localhost:4200");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
//        JSONObject obj = new JSONObject();
//
//        obj.put("count", session.getAttribute("count"));
//
//        pr.write(obj.toJSONString());

//
//        // Create Response Writer
//        PrintWriter out = response.getWriter();
//
//        // Initialize a Json Parser
//        JSONParser parser = new JSONParser();
//
//        // Get the HTTP Get Parameters
//        String fromString = request.getParameter("fromString");
//
//        JSONObject obj = new JSONObject();
//
//        //Check to see if we should use String to Object or Generate a New Object with puts.
//        if(fromString.compareTo("true") == 0){
//            // Test Creating a Json Object From String
//            String jsonInString = "{\"name\":\"mkyong\",\"age\":33,\"position\":\"Developer\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
//            try {
//                obj = (JSONObject) parser.parse(jsonInString);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }else{
//            // Test Creating a new Json Object and adding Individual Attributes with Put.
//            obj.put("name", "foo");
//            obj.put("num", new Integer(100));
//            obj.put("balance", new Double(1000.21));
//            obj.put("is_vip", new Boolean(true));
//        }
//
//        // Print Json Results.
//        out.write(obj.toJSONString());
//        out.flush();
    }



}
