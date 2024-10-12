package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.PaymentTypeDTO;
import com.upc.talkia_proyect.entities.PaymentType;
import com.upc.talkia_proyect.services.PaymentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final ModelMapper modelMapper = new ModelMapper();

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/paymentsType")
    public ResponseEntity<?> listPaymentTypes(){
        try {
            List<PaymentType> list = paymentTypeService.listPaymentTypes();
            List<PaymentTypeDTO> listDTO = modelMapper.map(list, List.class);
            return new ResponseEntity<>(listDTO, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
