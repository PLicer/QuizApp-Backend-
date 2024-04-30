package com.project.Quizapp.Dao;

import com.project.Quizapp.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findAllByCategory(String category);

    @Query(value ="SELECT * FROM question q Where q.category=:category ORDER BY RAND() LIMIT :numQ" ,nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
