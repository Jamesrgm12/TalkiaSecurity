package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.dtos.queries.*;
import com.upc.talkia_proyect.entities.SuscriptionsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface SuscriptionHistoryRepository extends JpaRepository<SuscriptionsHistory, Integer> {
    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.user.name, sh.payment.id, sh.startDate, sh.payment.amount) " +
            "from SuscriptionsHistory sh where sh.user.id =:userId")
    List<HistoryByObjectDTO> listHistoryByUser(@Param("userId") int userId);

    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.user.name, sh.payment.id, sh.startDate, sh.payment.amount)  from SuscriptionsHistory sh where sh.payment.paymentType.name =:paymentTypeName")
    List<HistoryByObjectDTO> listHistoryByPaymentType(@Param("paymentTypeName")String paymentTypeName);

    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.user.name, sh.payment.id, sh.startDate, sh.payment.amount)" +
            "from SuscriptionsHistory sh where sh.user.id=:userId and sh.suscription.name=:suscriptionName")
    List<HistoryByObjectDTO> listHistoryByUserAndSuscription(@Param("userId") int userId, @Param("suscriptionName") String suscriptionName);

    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.user.name, sh.payment.id, sh.startDate, sh.payment.amount)" +
            "from SuscriptionsHistory sh where sh.user.id=:userId and sh.payment.paymentType.name=:paymentTypeName")
    public List<HistoryByObjectDTO> listHistoryByUserAndPaymentType(@Param("userId") int userId, @Param("paymentTypeName")String paymentTypeName);

    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.user.name, sh.payment.id, sh.startDate, sh.payment.amount)" +
            "from SuscriptionsHistory sh where sh.user.id=:userId and sh.payment.paymentType.name=:paymentTypeName and sh.suscription.name=:suscriptionName")
    public List<HistoryByObjectDTO> listHistoryByAllFilters(@Param("userId") int userId, @Param("paymentTypeName")String paymentTypeName, @Param("suscriptionName") String suscriptionName);

    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.user.name, sh.payment.id, sh.startDate, sh.payment.amount)" +
            "from SuscriptionsHistory sh where sh.payment.paymentType.name=:paymentTypeName and sh.suscription.name=:suscriptionName")
    public List<HistoryByObjectDTO> listHistoryByPaymentTypeAndSuscription(@Param("paymentTypeName")String paymentTypeName , @Param("suscriptionName") String suscriptionName);

    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.user.name, sh.payment.id, sh.startDate, sh.payment.amount)" +
            "from SuscriptionsHistory sh where sh.suscription.name =:suscriptionName")
    public List<HistoryByObjectDTO> listHistoryBySuscription(@Param("suscriptionName") String suscriptionName);

    @Query("select new com.upc.talkia_proyect.dtos.queries.TotalAmountBySubTypeDTO(sh.suscription.name,count (sh),sum(sh.payment.amount))" +
            "from SuscriptionsHistory sh where sh.startDate between :startDate and :endDate group by sh.suscription.name")
    List<TotalAmountBySubTypeDTO> listTotalAmountBySubType(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //Funciona
    @Query("select new com.upc.talkia_proyect.dtos.queries.CountHistoriesByObjectDTO(sh.payment.paymentType.name ,count(sh)) " +
            "from SuscriptionsHistory sh where sh.startDate between :startDate and :endDate group by sh.payment.paymentType.name ")
    List<CountHistoriesByObjectDTO> countHistoriesByPaymentType(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select sh from SuscriptionsHistory sh where sh.user.id =:userId and sh.status = 'Activado'")
    public SuscriptionsHistory getSuscriptionsHistoriesByActiveStatus(@Param("userId") int userId);

    @Query("SELECT sh FROM SuscriptionsHistory sh WHERE sh.status = 'Activado' AND sh.endDate <= :currentDate")
    List<SuscriptionsHistory> getActiveSubscriptions(@Param("currentDate") LocalDate currentDate);

}
