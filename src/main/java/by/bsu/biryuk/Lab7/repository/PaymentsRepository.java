package by.bsu.biryuk.Lab7.repository;

import org.springframework.stereotype.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.bsu.biryuk.Lab7.entities.Payment;

@Repository
public interface PaymentsRepository extends CrudRepository<Payment, Long> {
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.fromAccount.client.id = :clientId AND p.paymentDate BETWEEN :from AND :to")
    public Double getClientPaymentsSum(@Param("clientId") int client_id, Date from, Date to);
}
