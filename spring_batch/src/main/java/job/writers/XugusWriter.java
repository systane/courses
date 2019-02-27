package job.writers;

import br.com.spring_batch.entities.Xugus;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class XugusWriter implements ItemWriter<Xugus> {
    @Override
    public void write(List<? extends Xugus> items) throws Exception {

    }
}
