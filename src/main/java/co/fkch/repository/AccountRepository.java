package co.fkch.repository;

import co.fkch.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByEmail(String email);

    @Query(value="{ 'email' : ?0 }", fields="{ 'password' : 0}")
    Account findByEmailNoPassword(String firstname);

    Account findByConfirmationToken(String confirmationToken);
}
