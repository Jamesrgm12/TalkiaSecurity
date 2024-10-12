package com.upc.talkia_proyect.servicesImpl;

import com.upc.talkia_proyect.entities.PaymentType;
import com.upc.talkia_proyect.repositories.PaymentTypeRepository;
import com.upc.talkia_proyect.services.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Override
    public List<PaymentType> listPaymentTypes() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public PaymentType getPaymentTypeById(int id) {
        return paymentTypeRepository.findById(id).get();
    }
}
