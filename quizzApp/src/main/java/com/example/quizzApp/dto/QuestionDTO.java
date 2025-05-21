package com.example.quizzApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDTO {

    private String category;

    private String questionTitle;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String rightAnswer;

    private String difficultyLevel;
}
