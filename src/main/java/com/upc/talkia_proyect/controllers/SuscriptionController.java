package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.SuscriptionDTO;
import com.upc.talkia_proyect.entities.Suscription;
import com.upc.talkia_proyect.services.SuscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SuscriptionController {
    @Autowired
    private SuscriptionService suscriptionService;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/suscription")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SuscriptionDTO> listSuscription(){
        List<Suscription> suscriptions = suscriptionService.listSuscriptions();
        List<SuscriptionDTO> suscriptionDTOs = modelMapper.map(suscriptions, List.class);
        return suscriptionDTOs;
    }
}