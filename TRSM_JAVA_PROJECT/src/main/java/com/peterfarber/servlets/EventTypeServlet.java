package com.peterfarber.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterfarber.dao.EventTypeDao;
import com.peterfarber.pojo.EventType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "EventTypes", urlPatterns = { "/EventTypes" })
public class EventTypeServlet extends HttpServlet{


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String session = request.getParameter("JSESSIONID");

        String responseStr = "";


        if(!session.isEmpty()){

            ObjectMapper objectMapper = new ObjectMapper();

            EventTypeDao eventTypeDao = new EventTypeDao();
            List<EventType> eventTypes = eventTypeDao.retrieveAll();

            //Convert that array into a string!
            responseStr = objectMapper.writeValueAsString(eventTypes);

        }

        PrintWriter pr = response.getWriter();

        //Send the string as a response back to angular.
        pr.write(responseStr);

        pr.flush();

    }


}
