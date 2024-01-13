package org.example.studentlessonservlet.servlet.delete;

import org.example.studentlessonservlet.manager.LessonManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteLesson")
public class deleteLessonServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        lessonManager.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/lessons");
    }
}
