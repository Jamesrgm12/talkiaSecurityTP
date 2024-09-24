package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.queries.ShowQuestionsByQuizDTO;
import com.upc.talkia_proyect.services.QuizzesQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizzesQuestionController {
    @Autowired
    private QuizzesQuestionService qqService;

    @GetMapping("quizzesQuestion/getAveragePointsByUser/{userId}")
    @PreAuthorize("hasRole('USER')")
    public Double getAveragePoints(@PathVariable int userId) {
        return qqService.getAveragePoints(userId);
    }

    @GetMapping("quizzesQuestion/listTotalQuizzesCompleted/{userId}")
    @PreAuthorize("hasRole('USER')")
    public Long getTotalQuizzesCompleted(@PathVariable int userId) {
        return qqService.getTotalQuizzesCompleted(userId);
    }

    @GetMapping("quizzesQuestion/getAvgCorrectAnswers/{userId}")
    @PreAuthorize("hasRole('USER')")
    public Double getAverageCorrectAnswers(@PathVariable int userId){
        return qqService.getAverageCorrectAnswers(userId);
    }

    @PutMapping("/quizzesQuestion/answerQuestion/{qqId}/{userAnswer}")
    @PreAuthorize("hasRole('USER')")
    public String answerQuestion(@PathVariable int qqId, @PathVariable String userAnswer){
        return qqService.answerQuestion(qqId, userAnswer);
    }

    @GetMapping("quizzesQuestion/getSecondAttemptCorrect/{quizId}")
    @PreAuthorize("hasRole('USER')")
    public Integer getSecondAttemptCorrectAnswers(@PathVariable int quizId){
        return qqService.getSecondAttemptCorrectAnswers(quizId);
    }

    @GetMapping("quizzesQuestion/getCorrectAnswersCount/{quizId}")
    @PreAuthorize("hasRole('USER')")
    public Integer getCorrectAnswersCount(@PathVariable int quizId){
        return qqService.getCorrectAnswersCount(quizId);
    }

    @GetMapping("quizzesQuestion/getPercentageCorrectAnswers/{quizId}")
    @PreAuthorize("hasRole('USER')")
    public Double getPercentageCorrectAnswers(@PathVariable int quizId){
        return qqService.getPercentageCorrectAnswers(quizId);
    }

    @GetMapping("quizzesQuestion/listQuestionsByQuiz/{quizId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ShowQuestionsByQuizDTO> listQuestionsByQuizId(@PathVariable int quizId){
        return qqService.listQuestionsByQuizId(quizId);
    }
}
