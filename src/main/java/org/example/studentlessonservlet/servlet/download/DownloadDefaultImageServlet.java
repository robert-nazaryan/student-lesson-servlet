package org.example.studentlessonservlet.servlet.download;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/downloadDefaultImage")
public class DownloadDefaultImageServlet extends HttpServlet {
    public static final String DEFAULT_IMAGE_DIRECTORY = "D:\\servlet\\student-lesson-servlet\\src\\main\\webapp\\img\\defaultProfilePhoto.png";
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File imageFile = new File(DEFAULT_IMAGE_DIRECTORY);
        if (imageFile.exists()){
            try(FileInputStream inputStream = new FileInputStream(imageFile)){
                resp.setContentType("image/jpeg");
                resp.setContentLength((int) imageFile.length());
                OutputStream outputStream = resp.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer,0,bytesRead);
                }
            }
        }
    }
}
