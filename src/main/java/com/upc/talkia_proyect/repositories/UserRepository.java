package com.upc.talkia_proyect.repositories;

import com.upc.talkia_proyect.dtos.queries.ShowSuscriptionDetailsDTO;
import com.upc.talkia_proyect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserById(int userId);

    @Query("select u from User u where u.iCreatedAt between :startDate and :endDate")
    List<User> listUsersByRegisterDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("select sh.user from SuscriptionsHistory sh " +
            "where ( sh.status = :status and sh.endDate>=current date ) " +
            "or (sh.status = :status and sh.endDate>current date ) " +
            "and not exists (select 1 from SuscriptionsHistory sh2 where sh2.user = sh.user and sh2.status ='Activado' and sh.startDate>current date )")
    List<User> listUsersByStatus(@Param("status") String status);

    List<User> getUserByUserNameContains(String username);

    @Query("select new com.upc.talkia_proyect.dtos.queries.ShowSuscriptionDetailsDTO(sh.suscription.name, sh.payment.amount) from SuscriptionsHistory sh where sh.user.id =:userId and sh.status= 'Activado'")
    public ShowSuscriptionDetailsDTO getCurrentSuscription(@Param("userId") int userId);

    Optional<User> findByUserName(String username);
}

