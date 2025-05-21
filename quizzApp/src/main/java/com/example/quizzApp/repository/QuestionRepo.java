package com.example.quizzApp.repository;

import com.example.quizzApp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {

    Optional<Question> findQuestionByQuestionTitle(String questionTitle);

    List<Question> findQuestionByCategory(String category);
}
