package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.dtos.queries.ShowYearlyPaymentsDTO;
import com.upc.talkia_proyect.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowYearlyPaymentsDTO(s.name, p.amount, p.date) from Payment p JOIN SuscriptionsHistory sh on p.id=sh.payment.id " +
        "JOIN User u on sh.user.id=u.id " +
        "JOIN Suscription s on sh.suscription.id=s.id WHERE u.id = :userId")
    public List<ShowYearlyPaymentsDTO> listPaymentsByUser(@Param("userId") Integer user);
    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowYearlyPaymentsDTO(s.name, p.amount, p.date) from Payment p JOIN SuscriptionsHistory sh on p.id=sh.payment.id " +
            "JOIN User u on sh.user.id=u.id " +
            "JOIN Suscription s on sh.suscription.id=s.id WHERE u.id = :userId AND Year(p.date)=:year")
    public List<ShowYearlyPaymentsDTO>listPaymentsByYear(@Param("userId") Integer user, @Param("year") Integer year);
}
