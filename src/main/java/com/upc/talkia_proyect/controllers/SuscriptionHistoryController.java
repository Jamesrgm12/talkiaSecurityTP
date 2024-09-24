package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.queries.*;
import com.upc.talkia_proyect.services.SuscriptionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SuscriptionHistoryController {

    @Autowired
    private SuscriptionHistoryService suscriptionHistoryService;

    @PostMapping("/suscriptionHistory/{user_id}/{sus_id}/{paymentType_id}")
    @PreAuthorize("hasRole('USER')")
    public String insertInManyToManyTable(@PathVariable int user_id, @PathVariable int sus_id, @PathVariable int paymentType_id){
        return suscriptionHistoryService.insertInManyToManyTable(user_id, sus_id, paymentType_id);
    }

    @GetMapping("/suscriptionHistoryByUser/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<HistoryByObjectDTO> listHistoryByUser(@PathVariable int userId){
        return suscriptionHistoryService.listHistoryByUser(userId);
    }

    @GetMapping("/suscriptionHistoryByPaymenyType/{paymentTypeName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryByObjectDTO> listHistoryByPaymentType(@PathVariable String paymentTypeName) {
        return suscriptionHistoryService.listHistoryByPaymentType(paymentTypeName);
    }

    @GetMapping("/suscriptionHistoryByUser/listHistoryByUserSuscription/{userId}/{sName}")
    @PreAuthorize("hasRole('ADMIN')")
    List<HistoryByObjectDTO> listHistoryByUserAndSuscription(@PathVariable int userId, @PathVariable String sName){
        return suscriptionHistoryService.listHistoryByUserAndSuscription(userId, sName);
    }

    @GetMapping("suscriptionHistories/listHistoryBySuscription/{suscriptionName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryByObjectDTO> listHistoryBySuscription(@PathVariable String suscriptionName) {
        return suscriptionHistoryService.listHistoryBySuscription(suscriptionName);
    }

    @GetMapping("suscriptionHistories/listHistoryByPaymentTypeAndSuscription/{paymentTypeName}/{suscriptionName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryByObjectDTO> listHistoryByPaymentTypeAndSuscription(@PathVariable String paymentTypeName, @PathVariable String suscriptionName) {
        return suscriptionHistoryService.listHistoryByPaymentTypeAndSuscription(paymentTypeName, suscriptionName);
    }

    @GetMapping("suscriptionHistories/listHistoryByUserAndPayment/{userId}/{paymentTypeName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryByObjectDTO> listHistoryByUserAndPaymentType(@PathVariable int userId, @PathVariable String paymentTypeName) {
        return suscriptionHistoryService.listHistoryByUserAndPaymentType(userId, paymentTypeName);
    }

    @GetMapping("suscriptionHistories/listHistoryByAll/{userId}/{paymentTypeName}/{suscriptionName}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<HistoryByObjectDTO> listHistoryByAllFilters(@PathVariable int userId, @PathVariable String paymentTypeName, @PathVariable String suscriptionName){
        return suscriptionHistoryService.listHistoryByAllFilters(userId, paymentTypeName, suscriptionName);
    }

    @GetMapping("suscriptionHistories/countHistoriesByPaymentType/{startDate}/{endDate}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CountHistoriesByObjectDTO> countHistoriesByPaymentType(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return suscriptionHistoryService.countHistoriesByPaymentType(startDate, endDate);
    }

    @GetMapping("suscriptionHistories/listTotalAmountBySubType/{startDate}/{endDate}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TotalAmountBySubTypeDTO> listTotalAmountBySubType(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return suscriptionHistoryService.listTotalAmountBySubType(startDate, endDate);
    }

}
