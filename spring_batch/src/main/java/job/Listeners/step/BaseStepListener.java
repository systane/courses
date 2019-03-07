package job.Listeners.step;

import br.com.spring_batch.entities.HistoricoExecucaoJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class BaseStepListener implements StepExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseStepListener.class);

//    private HistoricoExecucaoJobService historicoExecucaoJobService;

//    protected abstract void logExecutionError(StepExecution stepExecution, HistoricoExecucaoJob historicoExecucaoJob);

    @Override
    public void beforeStep(StepExecution stepExecution) {
        LOGGER.info("Starting the step :)");
        HistoricoExecucaoJob historicoExecucaoJob = new HistoricoExecucaoJob();
        historicoExecucaoJob.setDataFimExecucao(LocalDateTime.now());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
//        try {
            List<Throwable> failureExceptions = stepExecution.getFailureExceptions();
//            historicoExecucaoJobService


//            logExecutionError(stepExecution, historicoExecucaoJob);
//            historicoExecucaoJobRepository.save(historicoExecucaoJob);

            if(!CollectionUtils.isEmpty(failureExceptions)){
                ExitStatus exitStatus = stepExecution.getExitStatus();
                String statusJob = exitStatus.getExitCode() + " - " + exitStatus.getExitDescription();

                LOGGER.info("Status do job: " + statusJob);
                LOGGER.info("Quantidade de exceptions: " + failureExceptions.size());
                failureExceptions.stream().forEach(exception -> {
                    LOGGER.info("Exception Message: " + exception.getMessage());
                    LOGGER.info("Exception Cause: " + exception.getCause().getMessage());
                });
            }
//        }

        return stepExecution.getExitStatus();
    }
}
