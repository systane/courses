package job.Listeners.readers;

import br.com.spring_batch.entities.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryItemListener implements ItemReadListener<Account> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepositoryItemListener.class);

    @Override
    public void beforeRead() {
        System.out.println("Starting the search in the Account table to find records that contains at email with '@hotmail'");
    }

    @Override
    public void afterRead(Account item) {
        System.out.println("It was found an Account: " + item.toString());
    }

    @Override
    public void onReadError(Exception ex) {
        LOGGER.info("An exception occurs at the READER of the 1 Step: " + ex.getMessage());
    }
}
