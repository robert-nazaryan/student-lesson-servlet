package org.example.studentlessonservlet.servlet.add;

import org.example.studentlessonservlet.manager.LessonManager;
import org.example.studentlessonservlet.manager.StudentManager;
import org.example.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/addStudent")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024
)
public class AddStudentServlet extends HttpServlet {
    private final StudentManager studentManager = new StudentManager();
    private final LessonManager lessonManager = new LessonManager();
    public static final String UPLOAD_DIRECTORY = "D:\\servlet\\student-lesson-servlet\\uploadDirectory";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("studentName");
        String studentSurname = req.getParameter("studentSurname");
        String studentEmail = req.getParameter("studentEmail");
        if (studentManager.get(studentEmail) == null) {
            req.getSession().setAttribute("msg", "Student with this email is already there!");
            resp.sendRedirect("/addStudent");
            return;
        }
        int studentAge = Integer.parseInt(req.getParameter("studentAge"));
        int studentLessonId = Integer.parseInt(req.getParameter("studentLessonId"));
        Part studentPhoto = req.getPart("studentPhoto");
        String studentPhotoName = null;
        if (studentPhoto != null && studentPhoto.getSize() > 0) {
            studentPhotoName = System.currentTimeMillis() + "_" + studentPhoto.getSubmittedFileName();
            studentPhoto.write(UPLOAD_DIRECTORY + File.separator + studentPhotoName);
        }
        studentManager.add(Student.builder()
                .name(studentName)
                .surname(studentSurname)
                .email(studentEmail)
                .age(studentAge)
                .lesson(lessonManager.get(studentLessonId))
                .photoName(studentPhotoName)
                .build());
        resp.sendRedirect("/students");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lessons", lessonManager.getAll());
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }
}
