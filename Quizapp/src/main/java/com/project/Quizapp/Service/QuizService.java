package com.project.Quizapp.Service;

import com.project.Quizapp.Dao.QuestionDao;
import com.project.Quizapp.Dao.QuizDao;
import com.project.Quizapp.Entity.Question;
import com.project.Quizapp.Entity.QuestionWrapper;
import com.project.Quizapp.Entity.Quiz;
import com.project.Quizapp.Entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        Quiz quiz=new Quiz();
        List<Question>questions=questionDao.findRandomQuestionsByCategory(category,numQ);
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz>quiz=quizDao.findById(id);
        List<Question>questions=quiz.get().getQuestions();
        List<QuestionWrapper>questionWrappers=new ArrayList<>();
        for(Question question:questions)
        {
            QuestionWrapper questionWrapper=new QuestionWrapper(question.getId(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4(),question.getQuestionTitle());
            questionWrappers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        Optional<Quiz>quiz=quizDao.findById(id);
        //List<Question>questions=quiz.get().getQuestions();
        int i=0;
        int right=0;
        for(Response response:responses)
        {
            if(response.getResponse().equals(questionDao.findById(response.getId()).get().getRightAnswer()))
            {
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
