package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.queries.ShowContentHistoryDTO;
import com.upc.talkia_proyect.dtos.queries.ShowHistorialContentDTO;
import com.upc.talkia_proyect.services.UserContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserContentController {

    @Autowired
    private UserContentService userContentService;

    @PostMapping("/user_content/{contentId}/{userId}")
    @PreAuthorize("hasRole('USER')")
    public Integer InsertUserContent(@PathVariable(name="contentId") Integer contentId,
                                     @PathVariable(name = "userId") Integer userId) {
        return userContentService.insertUserContent(contentId, userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user_content/listar")
    public List<ShowHistorialContentDTO> listUserContent(){
        return userContentService.listUserContent();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/content_history_by_user/{userId}")
    public List<ShowContentHistoryDTO> ListUserContentByUser(@PathVariable int userId){
        return userContentService.ListUserContentByUser(userId);
    }

}
