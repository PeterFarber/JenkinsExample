package com.peterfarber.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterfarber.dao.ApplicationStatusDao;
import com.peterfarber.pojo.ApplicationStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Statuses", urlPatterns = { "/Statuses" })
public class StatusesServlet extends HttpServlet{


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String session = request.getParameter("JSESSIONID");

        String responseStr = "";


        if(!session.isEmpty()){

            ObjectMapper objectMapper = new ObjectMapper();

            String statusString = request.getParameter("Status");
            ApplicationStatusDao applicationStatusDao = new ApplicationStatusDao();
            List<ApplicationStatus> accountStatuses = applicationStatusDao.retrieveAll();
            System.out.println(accountStatuses.toString());
            responseStr = objectMapper.writeValueAsString(accountStatuses);

        }

        PrintWriter pr = response.getWriter();

        //Send the string as a response back to angular.
        pr.write(responseStr);

        pr.flush();

    }


}
