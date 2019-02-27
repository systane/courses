package job.processors;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.entities.Xugus;
import org.springframework.batch.item.ItemProcessor;

public class AccountProcessor implements ItemProcessor<Account, Xugus> {
    @Override
    public Xugus process(Account item) throws Exception {
        return null;
    }
}
