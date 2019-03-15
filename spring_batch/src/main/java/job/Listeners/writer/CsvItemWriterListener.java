package job.Listeners.writer;

import br.com.spring_batch.JobRunner;
import br.com.spring_batch.entities.HistoricoExecucaoItemJob;
import br.com.spring_batch.entities.HistoricoExecucaoJob;
import br.com.spring_batch.entities.Person;
import br.com.spring_batch.services.HistoricoExecucaoJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvItemWriterListener implements ItemWriteListener<Person> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvItemWriterListener.class);

    private final HistoricoExecucaoJobService historicoExecucaoJobService;

    @Value("#{jobParameters['idHistorico']}")
    private JobParameters jobParameters;


//    @BeforeStep
//    public void beforeStep(final StepExecution stepExecution){
//        jobParameters = stepExecution.getJobExecution().getJobParameters();
//    }

    @Autowired
    public CsvItemWriterListener(HistoricoExecucaoJobService historicoExecucaoJobService) {
        this.historicoExecucaoJobService = historicoExecucaoJobService;
//        this.jobParameters = jobParameters;
    }

    @Override
    public void beforeWrite(List<? extends Person> items) {
        LOGGER.info("Passo 1 - Iniciando a escrita dos seguintes itens: {} no arquivo CSV", items);
    }

    @Override
    public void afterWrite(List<? extends Person> items) {
        LOGGER.info("Passo 1 - Escrita realizada com sucesso");
        LOGGER.info("Passo 1 - Salvando histórico dos itens processados");
        try{

            long idHistorico = jobParameters.getLong(JobRunner.PARAMETRO_ID_HISTORICO);
            HistoricoExecucaoJob historicoExecucaoJob = historicoExecucaoJobService.findById(idHistorico);

            items.forEach(account -> {
                HistoricoExecucaoItemJob historicoExecucaoItemJob = new HistoricoExecucaoItemJob();
                historicoExecucaoItemJob.setFluxo_id(1L);
                historicoExecucaoItemJob.setTrilha_id(1L);
                historicoExecucaoJob.getHistoricoExecucaoItemJobList().add(historicoExecucaoItemJob);
            });

            historicoExecucaoJobService.save(historicoExecucaoJob);

            LOGGER.info("Passo 1 - Histórico dos itens processados salvo com sucesso");
        }
        catch (Exception e){
            LOGGER.error("Passo 1 - Falha ao salvar o histórico dos seguintes itens processados, {}", items);
            LOGGER.error(e.getStackTrace().toString());

            //TODO: Analisar como será realizado o tratamento de erro nesse caso

        }
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Person> items) {
        LOGGER.info("Erro ao tentar escrever os seguintes itens no arquivo CSV, {}", items);
    }
}
