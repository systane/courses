package job.readers.listeners;

import br.com.spring_batch.entities.Account;
import org.springframework.batch.core.ItemReadListener;

public class AccountReaderListener implements ItemReadListener<Account> {
    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Account item) {

    }

    @Override
    public void onReadError(Exception ex) {

    }
}
