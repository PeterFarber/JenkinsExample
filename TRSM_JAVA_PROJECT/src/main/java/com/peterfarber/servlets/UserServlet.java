package com.peterfarber.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterfarber.dao.UserDao;
import com.peterfarber.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "User", urlPatterns = { "/User" })
public class UserServlet extends HttpServlet{


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String session = request.getParameter("JSESSIONID");
        String userID = request.getParameter("UserID");

        String responseStr = "";


        if(!session.isEmpty()){

            ObjectMapper objectMapper = new ObjectMapper();

            //Get users that have this user as their supervisor.
            UserDao userDao = new UserDao();

            //Get all users where their supervisor is the logged user.
            User user = userDao.retrieveById(Integer.parseInt(userID));

            //Convert that array into a string!
            responseStr = objectMapper.writeValueAsString(user);

        }

        PrintWriter pr = response.getWriter();

        //Send the string as a response back to angular.
        pr.write(responseStr);

        pr.flush();

    }


}
