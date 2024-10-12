package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
    public PaymentType getPaymentTypeById(int id);


}
