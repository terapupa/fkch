package co.fkch.service;

import co.fkch.domain.Account;
import co.fkch.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findByEmailNoPassword(String email) {
        return accountRepository.findByEmailNoPassword(email);
    }

    public Account findByConfirmationToken(String confirmationToken) {
        return accountRepository.findByConfirmationToken(confirmationToken);
    }

    public Account saveUser(Account account) {
        return accountRepository.save(account);
    }
}
