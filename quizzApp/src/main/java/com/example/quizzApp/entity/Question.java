package com.example.quizzApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category" , nullable = false)
    private String category;

    @Column(name = "question_title" , nullable = false)
    private String questionTitle;

    @Column(name = "option1" , nullable = false)
    private String option1;

    @Column(name = "option2" , nullable = false)
    private String option2;

    @Column(name = "option3" , nullable = false)
    private String option3;

    @Column(name = "option4" , nullable = false)
    private String option4;

    @Column(name = "right_answer" , nullable = false)
    private String rightAnswer;

    @Column(name = "difficulty_level" , nullable = false)
    private String difficultyLevel;
}
