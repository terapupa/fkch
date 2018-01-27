package co.fkch.service;

import co.fkch.domain.User;
import co.fkch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmailNoPassword(String email) {
        return userRepository.findByEmailNoPassword(email);
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
