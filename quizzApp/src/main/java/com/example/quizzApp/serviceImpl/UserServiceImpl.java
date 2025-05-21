package com.example.quizzApp.serviceImpl;

import com.example.quizzApp.dto.UserDTO;
import com.example.quizzApp.entity.User;
import com.example.quizzApp.exception.QuizzAppException;
import com.example.quizzApp.repository.UserRepo;
import com.example.quizzApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final UserRepo userRepo;

    @Override
    public void signUp(UserDTO userDTO) throws QuizzAppException{
        if (!isStrongPassword(userDTO.getPassword())){
            throw new QuizzAppException("Please enter strong password, at least one uppercase letter, \" + \"one lowercase letter, one digit, and one special character needed");
        }
        Optional<User> usersOptional = userRepo.findByEmail(userDTO.getEmail());
        if (usersOptional.isPresent()){
            throw new QuizzAppException("User already registered, Sign in");
        }
        else{
            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setActive(true);
            userRepo.save(user);
        }
    }

    @Override
    public String login(String email, String password) throws QuizzAppException{
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()){
            throw new QuizzAppException("User not registered with provided E-Mail");
        }
        if (!(optionalUser.get().getPassword().equals(password))){
            throw new QuizzAppException("Password mismatch...!!");
        }
        return "Logged-In successfully";
    }

    @Override
    public void updatePassword(String email, String oldPassword, String newPassword){
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()){
            throw new QuizzAppException("User not registered with provided E-Mail");
        }
        if (!(optionalUser.get().getPassword().equals(oldPassword))){
            throw new QuizzAppException("You have entered incorrect old password");
        }
        if (!isStrongPassword(newPassword)){
            throw new QuizzAppException("Please enter strong password, at least one uppercase letter, \" + \"one lowercase letter, one digit, and one special character needed");
        }
        else {
            User user = optionalUser.get();
            user.setPassword(newPassword);
            userRepo.save(user);
        }
    }

    @Override
    public UserDTO fetchUserByEmail(String email) throws QuizzAppException{
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()){
            throw new QuizzAppException("User not found with provided details");
        }
        User user = optionalUser.get();
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public List<UserDTO> fetchAllUsers() throws QuizzAppException{
        List<User> userList = userRepo.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        if (userList.isEmpty()){
            throw new QuizzAppException("No users found");
        }
        for (User user : userList){
            UserDTO userDTO = UserDTO.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public void updateUserDetails(String firstName, String lastName, String email, long phoneNumber) throws QuizzAppException{
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()){
            throw new QuizzAppException("User not found with provided details");
        }
        User user = optionalUser.get();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        userRepo.save(user);
    }

    @Override
    public void deleteUser(String email) throws QuizzAppException{
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()){
            throw new QuizzAppException("User not found with provided details");
        }
        userRepo.delete(optionalUser.get());
    }



//===============================================================================================================================


    private boolean isStrongPassword(String password) {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (isSpecialChar(ch)) {
                hasSpecialChar = true;
            }
        }

        return password.length() >= 8 && hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }
    private boolean isSpecialChar(char ch) {
        String specialChars = "!@#$%^&*()_-+=[{]};:<>|./?";
        return specialChars.contains(Character.toString(ch));
    }
}
