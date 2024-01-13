package org.example.studentlessonservlet.servlet.delete;

import org.example.studentlessonservlet.manager.StudentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteStudent")
public class deleteStudentServlet extends HttpServlet {
    private StudentManager studentManager = new StudentManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        studentManager.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/students");
    }
}
