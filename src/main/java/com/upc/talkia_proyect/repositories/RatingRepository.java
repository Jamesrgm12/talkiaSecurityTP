package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.dtos.queries.ShowRatingByContentDTO;
import com.upc.talkia_proyect.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowRatingByContentDTO(r.content.title, avg(r.score)) from Rating r group by r.content.title")
    public List<ShowRatingByContentDTO> ListContentOrderByScore();
}
