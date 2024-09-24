package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.QuestionDTO;
import com.upc.talkia_proyect.dtos.queries.ShowQuestionByLevelDTO;
import com.upc.talkia_proyect.entities.Question;
import com.upc.talkia_proyect.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    ModelMapper modelMapper = new ModelMapper();

    @PutMapping("/question")
    @PreAuthorize("hasRole('ADMIN')")
    public QuestionDTO updateQuestion(@RequestBody QuestionDTO questionDTO){
        ModelMapper modelMapper = new ModelMapper();
        Question question = modelMapper.map(questionDTO, Question.class);
        question = questionService.updateQuestion(question);
        return modelMapper.map(question, QuestionDTO.class);
    }

    @PostMapping("/question")
    @PreAuthorize("hasRole('ADMIN')")
    public QuestionDTO insertQuestion(@RequestBody QuestionDTO questionDTO){
        Question question = modelMapper.map(questionDTO, Question.class);
        question = questionService.insertQuestion(question);
        return modelMapper.map(question, QuestionDTO.class);
    }

    @GetMapping("/questions")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<QuestionDTO> listQuestions(){
        List<Question>list = questionService.listQuestions();
        ModelMapper modelMapper = new ModelMapper();
        List<QuestionDTO> listDTO = modelMapper.map(list, List.class);
        return listDTO;
    }

    @GetMapping("/level/{level}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ShowQuestionByLevelDTO>listQuestionsByLevel(@PathVariable String level){
        return questionService.listQuestionsByLevel(level);
    }

    @DeleteMapping("/question/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteQuestion(@PathVariable int id){
        questionService.deleteQuestion(id);
    }

}
