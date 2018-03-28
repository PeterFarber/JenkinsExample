package com.peterfarber.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterfarber.dao.ApplicationDao;
import com.peterfarber.dao.UserDao;
import com.peterfarber.pojo.Application;
import com.peterfarber.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Application", urlPatterns = {"/Application"})
public class ApplicationsServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String session = request.getParameter("JSESSIONID");
        String userID = request.getParameter("UserID");
        String type = request.getParameter("Type");


//        //File Stuff
//        Part data = request.getPart("file");
//        String fileName = Paths.get(data.getSubmittedFileName()).getFileName().toString();
//        InputStream inputStream = data.getInputStream();
//
//        OutputStream outputStream = new FileOutputStream(new File("C:/Revature/TRSM_JAVA_PROJECT/files/"+ fileName));
//
//        int read = 0;
//        byte[] bytes = new byte[10485760];
//
//        while ((read = inputStream.read(bytes)) != -1) {
//            outputStream.write(bytes, 0, read);
//        }

        //End of file stuff.

        String responseStr = "";

        PrintWriter pr = response.getWriter();
        ApplicationDao applicationDao = new ApplicationDao();

        List<Application> applications = new ArrayList<Application>();

        if (!session.isEmpty()) {

            ObjectMapper objectMapper = new ObjectMapper();

            //Get all users where their supervisor is the logged user.
            switch (type) {
                case "ApplicationsToApprove": {

                    UserDao userDao = new UserDao();
                    User user = userDao.retrieveById(Integer.parseInt(userID));
                    if (user.getDepartment() == 2) {
                        applications.addAll(applicationDao.retrieveAll());
                        for (int i = 0; i < applications.size(); i++) {
                            if (applications.get(i).getStatus() == 3 || applications.get(i).getStatus() == 5) {
                            } else {
                                applications.remove(i);
                                i = -1;
                            }
                        }
                    } else {
                        List<User> users = userDao.retrieveAllBySupervisorID(Integer.parseInt(userID));

                        //For each user pull all their applications and add them to a list of applications.
                        for (int i = 0; i < users.size(); i++) {
                            users.addAll(userDao.retrieveAllBySupervisorID(users.get(i).getId()));
                            applications.addAll(applicationDao.retrieveAllByEmployeeId(users.get(i).getId()));
                        }

                        for (int i = 0; i < applications.size(); i++) {
                            if (applications.get(i).getStatus() != user.getPosition() - 1) {
                                applications.remove(i);
                                i = -1;
                            }
                        }
                    }


                    responseStr = objectMapper.writeValueAsString(applications);

                }
                break;
                case "MyApplications": {

                    applications.addAll(applicationDao.retrieveAllByEmployeeId(Integer.parseInt(userID)));

                    responseStr = objectMapper.writeValueAsString(applications);

                }
                break;
                case "Submit": {
                    String applicationString = request.getParameter("Application");
                    int position = Integer.parseInt(request.getParameter("Position"));

                    Application application = objectMapper.readValue(applicationString, Application.class);


                    application.setStatus(position);
                    if (position > 3) {
                        application.setStatus(3);
                    }

                    applicationDao.createPreparedStmt(application);
                }
                break;

                case "Approve": {
                    String applicationID = request.getParameter("ApplicationID");

                    Application application = applicationDao.retrieveByString(applicationID);
                    application.setStatus(application.getStatus() + 1);
                    applicationDao.update(application);
                    System.out.println("Hello");
                }
                break;

            }


        }
        pr.write(responseStr);

        pr.flush();

    }

    private static String getValue(Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[20000];
        for (int length = 0; (length = reader.read(buffer)) > 0; ) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }
}
