package br.com.spring_batch.services;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account findByEmailIsLike(String email){
        return accountRepository.findByEmailIsLike(email);
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }
}
