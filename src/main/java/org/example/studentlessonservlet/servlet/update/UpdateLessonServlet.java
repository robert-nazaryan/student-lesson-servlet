package org.example.studentlessonservlet.servlet.update;

import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.model.Lesson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateLesson")
public class UpdateLessonServlet extends HttpServlet {
    private final LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("lesson", lessonManager.get(id));
        req.getRequestDispatcher("/WEB-INF/updateLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));
        String lessonName = req.getParameter("lessonName");
        double lessonDuration = Double.parseDouble(req.getParameter("lessonDuration"));
        String lessonLecturerName = req.getParameter("lessonLecturerName");
        double lessonPrice = Double.parseDouble(req.getParameter("lessonPrice"));
        lessonManager.update(Lesson.builder()
                .id(lessonId)
                .name(lessonName)
                .duration(lessonDuration)
                .lectureName(lessonLecturerName)
                .price(lessonPrice)
                .build());
        resp.sendRedirect("/lessons");
    }
}
