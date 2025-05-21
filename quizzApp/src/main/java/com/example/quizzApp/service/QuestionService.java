package com.example.quizzApp.service;

import com.example.quizzApp.dto.QuestionDTO;
import com.example.quizzApp.exception.QuizzAppException;

import java.util.List;

public interface QuestionService {

    void addQuestion(QuestionDTO questionDTO) throws QuizzAppException;

    QuestionDTO fetchQuestionByQuestionTitle(String questionTitle) throws QuizzAppException;

    List<QuestionDTO> fetchQuestionByCategory(String category) throws QuizzAppException;

    void deleteQuestionByQuestionTitle(String questionTitle) throws QuizzAppException;

    List<QuestionDTO> getAllQuestions() throws QuizzAppException;

    void updateQuestion(String questionTitle, String option1, String option2, String option3, String option4, String rightAnswer) throws  QuizzAppException;
}
