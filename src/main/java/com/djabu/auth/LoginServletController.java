package com.djabu.auth;

import com.djabu.model.UserModel;
import com.djabu.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServletController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        UserModel user = new UserService().authenticateUser(email,password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getFirstname());
            response.sendRedirect("home");
        }else {
            request.setAttribute("Message", "invalid credentials");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }

    }

}
