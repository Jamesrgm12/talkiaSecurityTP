package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.RatingDTO;
import com.upc.talkia_proyect.dtos.queries.ShowRatingByContentDTO;
import com.upc.talkia_proyect.entities.Rating;
import com.upc.talkia_proyect.services.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/rating/{id_content}/{id_user}/{score}")
    @PreAuthorize("hasRole('USER')")
    public Integer insertRating(@PathVariable int id_content, @PathVariable int id_user,
                                @PathVariable int score){
        return ratingService.insertRating(id_content, id_user, score);
    }

    @GetMapping("/ratingsContentByScore")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<ShowRatingByContentDTO> ListContentOrderByScore(){
        return ratingService.ListContentOrderByScore();
    }

    @GetMapping("/ratingsByUser/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RatingDTO> listRatingByUser(@PathVariable int userId){
        List<Rating> ratings = ratingService.listRatingByUser(userId);
        List<RatingDTO> ratingsDTO = modelMapper.map(ratings,List.class);
        return ratingsDTO;
    }
}
