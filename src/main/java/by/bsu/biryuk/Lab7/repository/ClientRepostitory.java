package by.bsu.biryuk.Lab7.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import by.bsu.biryuk.Lab7.entities.Client;

@Repository
public interface ClientRepostitory extends CrudRepository<Client, Long> {
    
}
