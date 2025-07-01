package com.djabu.service;

import com.djabu.dao.UserDAO;
import com.djabu.model.UserModel;
import com.djabu.util.PasswordUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    UserDAO userDAO = new UserDAO();

    public List<String> validateUser(UserModel userModel, String confirmPassword)
            throws SQLException {
        List<String> errors = new ArrayList<>();

        if (userModel.getIdentification() == null || userModel.getIdentification().isBlank()){
            errors.add("identificacion is required");
        }

        if (userModel.getFirstname() == null || userModel.getFirstname().isBlank()){
            errors.add("first name is required");
        }

        if (userModel.getEmail() == null || userModel.getEmail().isBlank()) {
            errors.add("email is required");
        }

        if (userModel.getPassword() == null || userModel.getPassword().isBlank()){
            errors.add("password is required");
        }

        if (!Objects.equals(userModel.getPassword(), confirmPassword)) {
            errors.add("password doesn't match");
        }

        if (userModel.getPassword().length() < 8){
            errors.add("password must be 8 characters at least");
        }

        if (userDAO.validateUserByIdentificationOrEmail(userModel.getIdentification(), userModel.getEmail())){
            errors.add("user already exist");
        }

        return errors;


    }

    public int SignUpUser(UserModel userModel){
        String hashedPassword = PasswordUtil.hash(userModel.getPassword());
        userModel.setPassword(hashedPassword);
        return userDAO.SignUpUser(userModel);
    }

    public UserModel authenticateUser ( String email, String password){
        UserModel user = UserDAO.getUserByEmail(email);
        if(user == null) return null;
        return PasswordUtil.matches(password, user.getPassword()) ? user : null;
    }
}
