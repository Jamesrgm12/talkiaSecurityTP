package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO;
import com.upc.talkia_proyect.entities.SuscriptionsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface SuscriptionHistoryRepository extends JpaRepository<SuscriptionsHistory, Integer> {
    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.suscription.name, sh.payment.paymentType.name, sh.payment.amount, sh.status, sh.startDate, sh.endDate) " +
            "from SuscriptionsHistory sh where sh.user.id =:userId")
    List<HistoryByObjectDTO> listHistoryByUser(@Param("userId") int userId);
    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.user.name, sh.suscription.name, sh.payment.amount, sh.status, sh.startDate, sh.endDate)  from SuscriptionsHistory sh where sh.payment.paymentType.name =:paymentTypeName")
    List<HistoryByObjectDTO> listHistoryByPaymentType(@Param("paymentTypeName")String paymentTypeName);
    @Query("select new com.upc.talkia_proyect.dtos.queries.HistoryByObjectDTO(sh.suscription.name, sh.payment.paymentType.name, sh.payment.amount, sh.status, sh.startDate, sh.endDate)" +
            " from SuscriptionsHistory sh where sh.user.id=:userId and sh.suscription.name=:suscriptionName")
    List<HistoryByObjectDTO> listHistoryByUserAndSuscription(@Param("userId") int userId, @Param("suscriptionName") String suscriptionName);




}
