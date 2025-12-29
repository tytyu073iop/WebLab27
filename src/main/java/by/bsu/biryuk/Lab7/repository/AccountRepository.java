package by.bsu.biryuk.Lab7.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import by.bsu.biryuk.Lab7.entities.Account;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.client.id = :client_id")
    public List<Account> getClientAccounts(@Param("client_id") int client_id);
}
