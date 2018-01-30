package co.fkch.repository;

import co.fkch.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByEmail(String email);

    Account findByEmailOrUserName(String email, String userName);

    @Query(value="{ 'email' : ?0 }", fields="{ 'password' : 0}")
    Account findByEmailNoPassword(String email);

    Account findByConfirmationToken(String confirmationToken);
}
