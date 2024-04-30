package com.project.Quizapp.Controller;

import com.project.Quizapp.Entity.Question;
import com.project.Quizapp.Entity.QuestionWrapper;
import com.project.Quizapp.Entity.Quiz;
import com.project.Quizapp.Entity.Response;
import com.project.Quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String>createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("/getQuizQue/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id)
    {
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer>getScore(@PathVariable Integer id ,@RequestBody List<Response> responses)
    {
        return quizService.calculateScore(id,responses);
    }

}
