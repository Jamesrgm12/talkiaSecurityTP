package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.PaymentTypeDTO;
import com.upc.talkia_proyect.entities.PaymentType;
import com.upc.talkia_proyect.services.PaymentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
public class PaymentTypeController {
    @Autowired
    private PaymentTypeService paymentTypeService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/paymentsType")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<PaymentTypeDTO> getPaymentTypes(){
        List<PaymentType> list = paymentTypeService.listPaymentTypes();
        List<PaymentTypeDTO> listDTO = modelMapper.map(list, List.class);
        return listDTO;
    }


}
