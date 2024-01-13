package org.example.studentlessonservlet.servlet.add;

import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.model.Lesson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lessonName = req.getParameter("lessonName");
        Double lessonDuration = Double.valueOf(req.getParameter("lessonDuration"));
        String lessonLecturerName = req.getParameter("lessonLecturerName");
        Double lessonPrice = Double.valueOf(req.getParameter("lessonPrice"));
        lessonManager.add(Lesson.builder()
                .name(lessonName)
                .duration(lessonDuration)
                .lectureName(lessonLecturerName)
                .price(lessonPrice)
                .build());
        resp.sendRedirect("/lessons");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req,resp);
    }
}
