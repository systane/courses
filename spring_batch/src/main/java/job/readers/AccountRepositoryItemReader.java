package job.readers;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.repositories.AccountRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class AccountRepositoryItemReader implements ItemReader<Account>, InitializingBean {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        List<Account> accounts = accountRepository.readAccount();
        System.out.println(accounts.toString());

        return accounts.get(0);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if(Objects.isNull(accountRepository)){
            throw new Exception("AccountRepository deve ser fornecido");
        }
    }
}
