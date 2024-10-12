package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.queries.*;
import com.upc.talkia_proyect.services.SuscriptionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SuscriptionHistoryController {

    @Autowired
    private SuscriptionHistoryService suscriptionHistoryService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/suscriptionHistory/{user_id}/{sus_id}/{paymentType_id}")
    public ResponseEntity<?> insertInManyToManyTable(@PathVariable int user_id, @PathVariable int sus_id, @PathVariable int paymentType_id){
        try {
            String responseMessage = suscriptionHistoryService.insertInManyToManyTable(user_id, sus_id, paymentType_id);
            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/suscriptionHistoryByUser/{userId}")
    public ResponseEntity<?> listHistoryByUser(@PathVariable int userId){
        try {
            List<HistoryByObjectDTO> historyDTOs = suscriptionHistoryService.listHistoryByUser(userId);
            return new ResponseEntity<>(historyDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/suscriptionHistoryByPaymenyType/{paymentTypeName}")
    public ResponseEntity<?> listHistoryByPaymentType(@PathVariable String paymentTypeName) {
        try {
            List<HistoryByObjectDTO> historyDTOs = suscriptionHistoryService.listHistoryByPaymentType(paymentTypeName);
            return new ResponseEntity<>(historyDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listHistoryByUserSuscription/{userId}/{sName}")
    public ResponseEntity<?> listHistoryByUserAndSuscription(@PathVariable int userId, @PathVariable String sName){
        try {
            List<HistoryByObjectDTO> historyDTOs = suscriptionHistoryService.listHistoryByUserAndSuscription(userId, sName);
            return new ResponseEntity<>(historyDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listHistoryBySuscription/{suscriptionName}")
    public ResponseEntity<?> listHistoryBySuscription(@PathVariable String suscriptionName) {
        try {
            List<HistoryByObjectDTO> historyDTOs = suscriptionHistoryService.listHistoryBySuscription(suscriptionName);
            return new ResponseEntity<>(historyDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listHistoryByPaymentTypeAndSuscription/{paymentTypeName}/{suscriptionName}")
    public ResponseEntity<?> listHistoryByPaymentTypeAndSuscription(@PathVariable String paymentTypeName, @PathVariable String suscriptionName) {
        try {
            List<HistoryByObjectDTO> historyDTOs = suscriptionHistoryService.listHistoryByPaymentTypeAndSuscription(paymentTypeName, suscriptionName);
            return new ResponseEntity<>(historyDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listHistoryByUserAndPayment/{userId}/{paymentTypeName}")
    public ResponseEntity<?> listHistoryByUserAndPaymentType(@PathVariable int userId, @PathVariable String paymentTypeName) {
        try {
            List<HistoryByObjectDTO> historyDTOs = suscriptionHistoryService.listHistoryByUserAndPaymentType(userId, paymentTypeName);
            return new ResponseEntity<>(historyDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listHistoryByAll/{userId}/{paymentTypeName}/{suscriptionName}")
    public ResponseEntity<?> listHistoryByAllFilters(@PathVariable int userId, @PathVariable String paymentTypeName, @PathVariable String suscriptionName){
        try {
            List<HistoryByObjectDTO> historyDTOs = suscriptionHistoryService.listHistoryByAllFilters(userId, paymentTypeName, suscriptionName);
            return new ResponseEntity<>(historyDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/countHistoriesByPaymentType/{startDate}/{endDate}")
    public ResponseEntity<?> countHistoriesByPaymentType(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        try {
            List<CountHistoriesByObjectDTO> historyDTOs = suscriptionHistoryService.countHistoriesByPaymentType(startDate, endDate);
            return new ResponseEntity<>(historyDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listTotalAmountBySubType/{startDate}/{endDate}")
    public ResponseEntity<?> listTotalAmountBySubType(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        try {
            List<TotalAmountBySubTypeDTO> reportDTOs = suscriptionHistoryService.listTotalAmountBySubType(startDate, endDate);
            return new ResponseEntity<>(reportDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
