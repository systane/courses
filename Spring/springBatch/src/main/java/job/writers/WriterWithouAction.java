package job.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class WriterWithouAction implements ItemWriter {
    @Override
    public void write(List items) throws Exception {
    }
}
