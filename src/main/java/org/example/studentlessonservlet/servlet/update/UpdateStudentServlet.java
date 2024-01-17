package org.example.studentlessonservlet.servlet.update;

import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.manager.StudentManager;
import org.example.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    private final StudentManager studentManager = new StudentManager();
    private final LessonManager lessonManager = new LessonManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String studentName = req.getParameter("studentName");
        String studentSurname = req.getParameter("studentSurname");
        String studentEmail = req.getParameter("studentEmail");
        int studentAge = Integer.parseInt(req.getParameter("studentAge"));
        int studentLessonId = Integer.parseInt(req.getParameter("studentLessonId"));
        studentManager.update(Student.builder()
                .id(id)
                .name(studentName)
                .surname(studentSurname)
                .email(studentEmail)
                .age(studentAge)
                .lesson(lessonManager.get(studentLessonId))
                .build());
        resp.sendRedirect("/students");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("lessons", lessonManager.getAll());
        req.setAttribute("student", studentManager.get(id));
        req.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(req, resp);
    }
}
