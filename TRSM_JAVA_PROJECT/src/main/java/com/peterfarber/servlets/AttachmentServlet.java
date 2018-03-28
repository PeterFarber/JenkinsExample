package com.peterfarber.servlets;

import com.peterfarber.dao.AttachmentDao;
import com.peterfarber.pojo.Attachment;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

@WebServlet(name = "Attachment", urlPatterns = { "/Attachment" })
@MultipartConfig
public class AttachmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part data = request.getPart("fileKey");
        String finalGrade = request.getParameter("finalGrade");
        String applicationID = request.getParameter("applicationID");

        String fileName = Paths.get(data.getSubmittedFileName()).getFileName().toString();

        AttachmentDao attachmentDao = new AttachmentDao();
        Attachment attachment = new Attachment();
        attachment.setDescription("Empty");
        attachment.setPath("C:/Revature/TRSM_JAVA_PROJECT/files/" + fileName);
        attachment.setApplication_ID(Integer.parseInt(applicationID));
        attachment.setType(0);

        System.out.println(attachment);

        attachmentDao.createPreparedStmt(attachment);

        InputStream inputStream = data.getInputStream();

        OutputStream outputStream = new FileOutputStream(new File("C:/Revature/TRSM_JAVA_PROJECT/files/" + fileName));

        int read = 0;
        byte[] bytes = new byte[1048576];

        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
    }

}
