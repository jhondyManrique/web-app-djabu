package com.djabu.controller;

import com.djabu.dao.UserDAO;
import com.djabu.model.UserModel;
import com.djabu.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@WebServlet("/users")
public class UserServletController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identification = request.getParameter("identification");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        UserModel userModel = new UserModel();
        userModel.setIdentification(identification);
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setPhone(phone);
        userModel.setEmail(email);
        userModel.setPassword(password);

        UserService userService = new UserService();

        List<String> errors = null;
        try {
            errors = userService.validateUser(userModel, confirmPassword);
            if (errors.isEmpty()){
                userService.SignUpUser(userModel);
                String successMessage = "your registration was success " + userModel.getFirstname();
                request.setAttribute("Message",successMessage);
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }else {
                request.setAttribute("errors", errors);
                request.setAttribute("userModel", userModel);
                request.setAttribute("errorMessage", "there are validation errors, please fix it and try again");
                request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
                System.out.println(errors);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
