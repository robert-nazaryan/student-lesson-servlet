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
        if (lessonManager.get(lessonName) == null) {
            req.getSession().setAttribute("msg", "Lesson with this name is already there!");
            resp.sendRedirect("/addLesson");
            return;
        }
        double lessonDuration = Double.parseDouble(req.getParameter("lessonDuration"));
        String lessonLecturerName = req.getParameter("lessonLecturerName");
        double lessonPrice = Double.parseDouble(req.getParameter("lessonPrice"));
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
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }
}
