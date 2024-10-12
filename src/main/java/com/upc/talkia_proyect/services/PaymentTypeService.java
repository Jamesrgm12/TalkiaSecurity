package com.upc.talkia_proyect.services;

import com.upc.talkia_proyect.entities.PaymentType;

import java.util.List;

public interface PaymentTypeService {
    public List<PaymentType> listPaymentTypes();
    public PaymentType getPaymentTypeById(int id);

}
