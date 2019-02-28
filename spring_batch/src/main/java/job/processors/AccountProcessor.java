package job.processors;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.entities.Xugus;
import br.com.spring_batch.repositories.AccountRepository;
import br.com.spring_batch.repositories.XugusRepository;
import br.com.spring_batch.services.AccountService;
import org.apache.commons.lang3.Validate;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountProcessor implements ItemProcessor<Account, Xugus> , InitializingBean {

    private AccountRepository accountRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        Validate.notNull(accountRepository, "accountRepository deve ser fornecido");
    }

    public void setAccountRepository(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    @Override
    public Xugus process(Account account) throws Exception {
        Xugus xugus = new Xugus();

        xugus.setPerson_id(account.getPerson_id());
        xugus.setNome("Xugao");

        String[] email = account.getEmail().split("@");
        String aliasEmail = email[0];
        String novoEmail = aliasEmail + "@outlook.com";

        Account updatedAccount = accountRepository.alterAccount(account.getPerson_id(), novoEmail);
        System.out.println(updatedAccount.toString());

//        Xugus xugusSalvo = xugusRepository.insert(xugus);

        return xugus;
    }
}
