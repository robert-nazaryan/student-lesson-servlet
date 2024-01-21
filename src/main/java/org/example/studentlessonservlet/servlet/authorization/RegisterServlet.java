package org.example.studentlessonservlet.servlet.authorization;

import org.example.studentlessonservlet.manager.UserManager;
import org.example.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private final UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        if(userManager.getByEmail(email) != null){
            req.getSession().setAttribute("msg", "User with this email is already there!");
            resp.sendRedirect("/app");
            return;
        }
        String password = req.getParameter("password");
        userManager.add(User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .build());
        req.getSession().setAttribute("msg", "You are registered!");
        resp.sendRedirect("/app");
    }
}
