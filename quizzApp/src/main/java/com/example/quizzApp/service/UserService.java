package com.example.quizzApp.service;

import com.example.quizzApp.dto.UserDTO;
import com.example.quizzApp.exception.QuizzAppException;

import java.util.List;

public interface UserService {

    void signUp(UserDTO usersDTO) throws QuizzAppException;

    String login(String email, String password) throws QuizzAppException;

    void updatePassword(String email, String oldPassword, String newPassword);

    UserDTO fetchUserByEmail(String email) throws QuizzAppException;

    List<UserDTO> fetchAllUsers() throws QuizzAppException;

    void updateUserDetails(String firstName, String lastName, String email, long phoneNumber) throws QuizzAppException;

    void deleteUser(String email) throws QuizzAppException;
}
