package job.writers;

import br.com.spring_batch.entities.Xugus;
import br.com.spring_batch.repositories.AccountRepository;
import br.com.spring_batch.repositories.XugusRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class XugusWriter implements ItemWriter<Xugus>, InitializingBean {

    private XugusRepository xugusRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        Validate.notNull(xugusRepository, "xugusRepository deve ser fornecido");
    }

    public void setXugusRepository(XugusRepository xugusRepository){
        this.xugusRepository = xugusRepository;
    }

    @Override
    public void write(List<? extends Xugus> items) throws Exception {
        Xugus xugus = items.get(0);

        Xugus xugusSalvo = xugusRepository.insert(xugus);
        System.out.println(xugusSalvo);
    }
}
