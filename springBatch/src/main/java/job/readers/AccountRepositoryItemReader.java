package job.readers;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.services.AccountService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryItemReader implements ItemReader<Account> {

    private final AccountService accountService;

    @Autowired
    public AccountRepositoryItemReader(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Account read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Account account = accountService.findByEmailIsLike("%@hotmail.com");

        return account;
    }
}
