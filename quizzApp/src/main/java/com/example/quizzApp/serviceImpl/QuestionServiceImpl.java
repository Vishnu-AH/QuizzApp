package com.example.quizzApp.serviceImpl;

import com.example.quizzApp.dto.QuestionDTO;
import com.example.quizzApp.entity.Question;
import com.example.quizzApp.exception.QuizzAppException;
import com.example.quizzApp.repository.QuestionRepo;
import com.example.quizzApp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;

    @Override
    public void addQuestion(QuestionDTO questionDTO) throws QuizzAppException{
        Optional<Question> optionalQuestion = questionRepo.findQuestionByQuestionTitle(questionDTO.getQuestionTitle().toLowerCase());
        if (optionalQuestion.isPresent()){
            throw new QuizzAppException("Question already exists");
        }
        else {
            Question question = new Question();
            question.setCategory(questionDTO.getCategory().toLowerCase());
            question.setDifficultyLevel(questionDTO.getDifficultyLevel().toLowerCase());
            question.setQuestionTitle(questionDTO.getQuestionTitle().toLowerCase());
            question.setOption1(questionDTO.getOption1().toLowerCase());
            question.setOption2(questionDTO.getOption2().toLowerCase());
            question.setOption3(questionDTO.getOption3().toLowerCase());
            question.setOption4(questionDTO.getOption4().toLowerCase());
            question.setRightAnswer(questionDTO.getRightAnswer().toLowerCase());
            questionRepo.save(question);
        }
    }

    @Override
    public QuestionDTO fetchQuestionByQuestionTitle(String questionTitle) throws QuizzAppException{
        Optional<Question> optionalQuestion = questionRepo.findQuestionByQuestionTitle(questionTitle.toLowerCase());
        if (optionalQuestion.isEmpty()){
            throw new QuizzAppException("Question does not exist");
        }
        else{
            Question question = optionalQuestion.get();
            return QuestionDTO.builder()
                    .category(question.getCategory())
                    .difficultyLevel(question.getDifficultyLevel())
                    .questionTitle(question.getQuestionTitle())
                    .option1(question.getOption1())
                    .option2(question.getOption2())
                    .option3(question.getOption3())
                    .option4(question.getOption4())
                    .rightAnswer(question.getRightAnswer())
                    .build();
        }
    }

    @Override
    public List<QuestionDTO> fetchQuestionByCategory(String category) throws QuizzAppException{
        List<Question> questionList = questionRepo.findQuestionByCategory(category.toLowerCase());
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (questionList.isEmpty()){
            throw new QuizzAppException("No questions in given category");
        }
        else{
            for (Question question:questionList){
                QuestionDTO questionDTO = QuestionDTO.builder()
                        .category(question.getCategory())
                        .difficultyLevel(question.getDifficultyLevel())
                        .questionTitle(question.getQuestionTitle())
                        .option1(question.getOption1())
                        .option2(question.getOption2())
                        .option3(question.getOption3())
                        .option4(question.getOption4())
                        .rightAnswer(question.getRightAnswer())
                        .build();
                questionDTOList.add(questionDTO);
            }
        }
        return questionDTOList;
    }

    @Override
    public List<QuestionDTO> getAllQuestions() throws QuizzAppException {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionRepo.findAll();
        if(questionList.isEmpty()){
            throw new QuizzAppException("Data not found");
        }
        else{
            for (Question question:questionList) {
                QuestionDTO questionDTO = QuestionDTO.builder()
                        .category(question.getCategory())
                        .difficultyLevel(question.getDifficultyLevel())
                        .questionTitle(question.getQuestionTitle())
                        .option1(question.getOption1())
                        .option2(question.getOption2())
                        .option3(question.getOption3())
                        .option4(question.getOption4())
                        .rightAnswer(question.getRightAnswer())
                        .build();
                questionDTOList.add(questionDTO);
            }
        }
        return questionDTOList;
    }

    @Override
    public void deleteQuestionByQuestionTitle(String questionTitle) throws QuizzAppException{
        Optional<Question> optionalQuestion = questionRepo.findQuestionByQuestionTitle(questionTitle.toLowerCase());
        if (optionalQuestion.isEmpty()){
            throw new QuizzAppException("Question not found");
        }
        questionRepo.delete(optionalQuestion.get());
    }

    @Override
    public void updateQuestion(String questionTitle, String option1, String option2, String option3, String option4, String rightAnswer) throws  QuizzAppException{
        Optional<Question> optionalQuestion = questionRepo.findQuestionByQuestionTitle(questionTitle.toLowerCase());
        if (optionalQuestion.isEmpty()){
            throw new QuizzAppException("Question not found...!!");
        }
        else {
            Question question = optionalQuestion.get();
            question.setOption1(option1.toLowerCase());
            question.setOption2(option2.toLowerCase());
            question.setOption3(option3.toLowerCase());
            question.setOption4(option4.toLowerCase());
            question.setRightAnswer(rightAnswer.toLowerCase());
            questionRepo.save(question);
        }
    }
}