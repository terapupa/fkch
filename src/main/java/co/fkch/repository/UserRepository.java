package co.fkch.repository;

import co.fkch.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);
}
