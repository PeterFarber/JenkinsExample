package com.peterfarber.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterfarber.dao.GradingFormatDao;
import com.peterfarber.pojo.GradingFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GradingFormats", urlPatterns = { "/GradingFormats" })
public class GradingFormatServlet extends HttpServlet{


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String session = request.getParameter("JSESSIONID");

        String responseStr = "";


        if(!session.isEmpty()){

            ObjectMapper objectMapper = new ObjectMapper();

            GradingFormatDao gradingFormatDao = new GradingFormatDao();
            List<GradingFormat> gradingFormats = gradingFormatDao.retrieveAll();

            //Convert that array into a string!
            responseStr = objectMapper.writeValueAsString(gradingFormats);

        }

        PrintWriter pr = response.getWriter();

        //Send the string as a response back to angular.
        pr.write(responseStr);

        pr.flush();

    }


}
