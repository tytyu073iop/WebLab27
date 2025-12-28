package by.bsu.biryuk.Lab7.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.bsu.biryuk.Lab7.entities.Account;
import by.bsu.biryuk.Lab7.entities.CreditCard;

@Repository
public interface CreditCardsRepository extends CrudRepository<CreditCard, Long> {
    
    @Query("SELECT c FROM CreditCard c WHERE c.cardNumber = :cardNumber")
    public CreditCard getCreditCardByCardNumber(@Param("cardNumber") String cardNumber);

    @Modifying
    @Query(value = "INSERT INTO payment (from_account_id, to_account_id, amount, payment_date, status) VALUES ((SELECT account_id FROM credit_card WHERE card_id = :#{#fromCard.cardId}), :#{#toAccount.accountId}, :amount, CURRENT_TIMESTAMP, 'SUCCESS')", nativeQuery = true)
    public void makePayment(@Param("fromCard") CreditCard fromCard, @Param("toAccount") Account toAccount, @Param("amount") double amount);

    @Modifying
    @Query("UPDATE CreditCard c SET c.isActive = false WHERE c.cardId = :#{#card.cardId}")
    public void deactivateCard(@Param("card") CreditCard card);
}
