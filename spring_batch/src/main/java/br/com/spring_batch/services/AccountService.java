package br.com.spring_batch.services;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account readAccount() {
        return accountRepository.readAccount();
    }
}
