package com.upc.talkia_proyect.controllers;
import com.upc.talkia_proyect.dtos.LevelDTO;
import com.upc.talkia_proyect.entities.Level;
import com.upc.talkia_proyect.services.LevelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LevelController {

    @Autowired
    private LevelService levelService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/levels")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<LevelDTO> listLevels(){
        List<Level> levels=levelService.listLevels();
        ModelMapper modelMapper = new ModelMapper();
        List<LevelDTO> levelsDTO =modelMapper.map(levels, List.class);
        return levelsDTO;
    }

}
