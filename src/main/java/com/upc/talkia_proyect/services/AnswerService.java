package com.upc.talkia_proyect.services;

import com.upc.talkia_proyect.dtos.queries.ShowAnswersByQuestionAdminDTO;
import com.upc.talkia_proyect.dtos.queries.ShowAnswersByQuestionUserDTO;
import com.upc.talkia_proyect.entities.Answer;

import java.util.List;

public interface AnswerService {
    public Answer insertAnswer(Answer answer);
    public List<ShowAnswersByQuestionAdminDTO> listAnswerByQuestionAdmin(int questionId);
    public List<ShowAnswersByQuestionUserDTO> listAnswerByQuestionUser(int questionId);
}
