package com.upc.talkia_proyect.services;

import com.upc.talkia_proyect.dtos.queries.ShowYearlyPaymentsDTO;
import com.upc.talkia_proyect.entities.Payment;

import java.util.List;

public interface PaymentService {
    public List<ShowYearlyPaymentsDTO> listPaymentsByUser(Integer userId);
    public List<ShowYearlyPaymentsDTO> listPaymentsByYear(Integer userId, Integer year);
    public List<Payment> listPayments();

}
