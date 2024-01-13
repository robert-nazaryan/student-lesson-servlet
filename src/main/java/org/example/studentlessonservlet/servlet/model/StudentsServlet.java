package org.example.studentlessonservlet.servlet.model;

import org.example.studentlessonservlet.manager.StudentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/students")
public class StudentsServlet extends HttpServlet {
    private final StudentManager studentManager = new StudentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentManager.getAll());
        req.getRequestDispatcher("/WEB-INF/students.jsp").forward(req, resp);
    }
}
