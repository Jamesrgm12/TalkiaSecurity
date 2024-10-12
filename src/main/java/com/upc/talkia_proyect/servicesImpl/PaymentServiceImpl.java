package com.upc.talkia_proyect.servicesImpl;

import com.upc.talkia_proyect.dtos.queries.ShowYearlyPaymentsDTO;
import com.upc.talkia_proyect.entities.Payment;
import com.upc.talkia_proyect.repositories.PaymentRepository;
import com.upc.talkia_proyect.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<ShowYearlyPaymentsDTO> listPaymentsByUser(Integer userId) {
        return paymentRepository.listPaymentsByUser(userId);
    }

    @Override
    public List<ShowYearlyPaymentsDTO> listPaymentsByYear(Integer userId, Integer year) {
        return paymentRepository.listPaymentsByYear(userId, year);
    }
    @Override
    public List<Payment> listPayments() {
        return paymentRepository.findAll();
    }

}
