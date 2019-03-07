package br.com.spring_batch.services;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final IAccountRepository accountRepository;

    @Autowired
    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account findByEmailisLike(String email){
        return accountRepository.findByEmailIsLike(email);
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }
}
