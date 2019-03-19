package job.processors;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.entities.Person;
import br.com.spring_batch.services.AccountService;
import br.com.spring_batch.services.PersonService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountProcessor implements ItemProcessor<Account, Person> {

    private final AccountService accountService;
    private final PersonService personService;

    @Autowired
    public AccountProcessor(AccountService accountService, PersonService personService) {
        this.accountService = accountService;
        this.personService = personService;
    }

    @Override
    public Person process(Account account) throws Exception {

        Person person = new Person();

        person.setName("joaozinho");
        person.setAccount_id(account.getAccount_id());

        String[] email = account.getEmail().split("@");
        String aliasEmail = email[0];
        String novoEmail = aliasEmail + "@outlook.com";
        account.setEmail(novoEmail);

        account = accountService.save(account);
        person = personService.save(person);

        System.out.println("Updated Account: " + account.toString());

        return person;
    }
}
