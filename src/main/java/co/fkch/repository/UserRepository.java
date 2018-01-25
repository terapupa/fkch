package co.fkch.repository;

import co.fkch.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    @Query(value="{ 'email' : ?0 }", fields="{ 'password' : 0}")
    User findByEmailNoPassword(String firstname);

    User findByConfirmationToken(String confirmationToken);
}
