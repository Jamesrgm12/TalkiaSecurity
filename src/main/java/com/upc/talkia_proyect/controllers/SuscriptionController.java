package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.SuscriptionDTO;
import com.upc.talkia_proyect.entities.Suscription;
import com.upc.talkia_proyect.services.SuscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SuscriptionController {

    @Autowired
    private SuscriptionService suscriptionService;

    private final ModelMapper modelMapper = new ModelMapper();

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/suscription")
    public ResponseEntity<?> listSuscription(){
        try {
            List<Suscription> suscriptions = suscriptionService.listSuscriptions();
            List<SuscriptionDTO> suscriptionDTOs = modelMapper.map(suscriptions, List.class);
            return new ResponseEntity<>(suscriptionDTOs, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}