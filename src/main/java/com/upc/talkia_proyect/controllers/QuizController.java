package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.QuizDTO;
import com.upc.talkia_proyect.dtos.queries.AveragePointsLevelDTO;
import com.upc.talkia_proyect.dtos.queries.QuizzesPerLevelDTO;
import com.upc.talkia_proyect.entities.Quiz;
import com.upc.talkia_proyect.services.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {
    @Autowired
    private QuizService quizService;

    ModelMapper modelMapper=new ModelMapper();

    @GetMapping("/quizzes")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<QuizDTO> listQuizzes(){
        List<Quiz> quizzes =quizService.listQuizzes();
        List<QuizDTO> quizDTOs= modelMapper.map(quizzes,List.class);
        return quizDTOs;
    }

    @PostMapping("/quiz/{userId}")
    @PreAuthorize("hasRole('USER')")
    public Quiz insertQuiz(@PathVariable int userId){
        return quizService.insertQuiz(userId);
    }

    @GetMapping("/quizzes/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Quiz> listQuizzesByUserId(@PathVariable int userId) {
        return quizService.listQuizzesByUserId(userId);
    }

    @GetMapping("/quizzes/average")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<AveragePointsLevelDTO> listAveragePoints(){
        return quizService.listAveragePoints();
    }

    @GetMapping("/quizzes/quantity")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<QuizzesPerLevelDTO> listQuizzesPerLevel(){
        return quizService.listQuizzesPerLevel();
    }
}
