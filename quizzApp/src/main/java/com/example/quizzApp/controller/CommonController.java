package com.example.quizzApp.controller;

import com.example.quizzApp.dto.QuestionDTO;
import com.example.quizzApp.dto.UserDTO;
import com.example.quizzApp.exception.QuizzAppException;
import com.example.quizzApp.responses.APISuccess;
import com.example.quizzApp.service.QuestionService;
import com.example.quizzApp.service.UserService;
import com.example.quizzApp.utility.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("public")
public class CommonController {

    private final QuestionService questionService;

    private final UserService userService;

    @PostMapping("user/signUp")
    public ResponseEntity<Object> userSignUp(@RequestBody UserDTO usersDTO) throws QuizzAppException{
        userService.signUp(usersDTO);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,"User sign up successful"));
    }

    @PostMapping("user/login")
    public ResponseEntity<Object> login(@RequestParam String email, @RequestParam String password) throws QuizzAppException {
        String loginMessage = userService.login(email, password);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,loginMessage));
    }

    @GetMapping("user/fetchBy/email")
    public ResponseEntity<Object> fetchUserByEmail(@RequestParam String email) throws QuizzAppException{
        UserDTO userDTO = userService.fetchUserByEmail(email);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK, userDTO));
    }

    @PostMapping("user/updatePassword")
    public ResponseEntity<Object> updatePassword(@RequestParam String email,@RequestParam String oldPassword,@RequestParam String newPassword){
        userService.updatePassword(email, oldPassword, newPassword);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,"Password update successful"));
    }

    @GetMapping("user/fetchAllUsers")
    public ResponseEntity<Object> fetchAllUsers() throws QuizzAppException{
        List<UserDTO> userDTOList = userService.fetchAllUsers();
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK, userDTOList));
    }

    @PutMapping("user/update/userDetails")
    public ResponseEntity<Object> updateUserDetails(@RequestParam String firstName,
                                                    @RequestParam String lastName,
                                                    @RequestParam String email,
                                                    @RequestParam long phoneNumber) throws QuizzAppException{
        userService.updateUserDetails(firstName,lastName,email,phoneNumber);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,"User details updated successfully"));
    }

    @DeleteMapping("user/deleteUser")
    public ResponseEntity<Object> deleteUser(@RequestParam String email) throws QuizzAppException{
        userService.deleteUser(email);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK, "User deletion successful"));
    }


//================================================================================================================================================


    @PostMapping("question/add")
    public ResponseEntity<Object> addQuestion(@RequestBody QuestionDTO questionDTO) throws QuizzAppException{
        questionService.addQuestion(questionDTO);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,"Question added successfully"));
    }

    @GetMapping("question/fetchBy/questionTitle")
    public ResponseEntity<Object> fetchQuestionByQuestionTitle(@RequestParam String questionTitle) throws QuizzAppException{
        QuestionDTO questionDTO = questionService.fetchQuestionByQuestionTitle(questionTitle);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,questionDTO));
    }

    @GetMapping("question/fetchBy/category")
    public ResponseEntity<Object> fetchQuestionByCategory(@RequestParam String category) throws QuizzAppException{
        List<QuestionDTO> questionDTOList = questionService.fetchQuestionByCategory(category);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,questionDTOList));
    }

    @GetMapping("question/fetch/allQuestions")
    public ResponseEntity<Object> getAllQuestions() throws QuizzAppException {
        List<QuestionDTO> questionDtoList = questionService.getAllQuestions();
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,questionDtoList));
    }

    @DeleteMapping("question/deleteBy/questionTitle")
    public ResponseEntity<Object> deleteQuestionByQuestionTitle(@RequestParam String questionTitle) throws QuizzAppException{
        questionService.deleteQuestionByQuestionTitle(questionTitle);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,"Question deleted successfully"));
    }

    @PutMapping("question/update")
    public ResponseEntity<Object> updateQuestion(@RequestParam String questionTitle,
                               @RequestParam String option1,
                               @RequestParam String option2,
                               @RequestParam String option3,
                               @RequestParam String option4,
                               @RequestParam String rightAnswer) throws  QuizzAppException{
        questionService.updateQuestion(questionTitle,option1,option2,option3,option4,rightAnswer);
        return CommonUtil.buildResponse(new APISuccess(HttpStatus.OK,"Update successful"));
    }
}