package job.readers;

import br.com.spring_batch.services.AccountService;
import br.com.spring_batch.entities.Account;
import org.apache.commons.lang3.Validate;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class AccountRepositoryItemReader implements ItemReader<Account>, InitializingBean {

    private AccountService accountService;

    @Override
    public Account read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Account account = accountService.readAccount();
        System.out.println(account.toString());

        return account;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Validate.notNull(accountService, "accountService deve ser fornecido");
    }

    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }
}
