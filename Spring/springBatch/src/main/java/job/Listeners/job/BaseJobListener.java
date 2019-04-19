package job.Listeners.job;

import br.com.spring_batch.JobRunner;
import br.com.spring_batch.entities.HistoricoExecucaoJob;
import br.com.spring_batch.services.HistoricoExecucaoJobService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.listener.StepListenerFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Component
public class BaseJobListener implements JobExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseJobListener.class);

    private final HistoricoExecucaoJobService historicoExecucaoJobService;

    @Autowired
    public BaseJobListener(HistoricoExecucaoJobService historicoExecucaoJobService) {
        this.historicoExecucaoJobService = historicoExecucaoJobService;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("Starting the job :)");

        try {
            HistoricoExecucaoJob historicoExecucaoJob = new HistoricoExecucaoJob();

            Date startTime = jobExecution.getStartTime();
            LocalDateTime startTimeLocalDateTime = startTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            historicoExecucaoJob.setDataInicioExecucao(startTimeLocalDateTime);
            historicoExecucaoJob = historicoExecucaoJobService.save(historicoExecucaoJob);

            jobExecution.getExecutionContext().put(JobRunner.PARAMETRO_ID_HISTORICO, historicoExecucaoJob
                    .getHistorico_execucao_id());
        }
        catch (Exception e){
            LOGGER.error(e.getStackTrace().toString());
            throw new StepListenerFailedException("Erro ao registrar o histórico e iniciar o Job", e);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("Finalizando o job...");

        try{
            Long idHistorico = jobExecution.getExecutionContext().getLong(JobRunner.PARAMETRO_ID_HISTORICO);

            if(Objects.nonNull(idHistorico)){
                HistoricoExecucaoJob historicoExecucaoJob = getHistoricoExecution(jobExecution, idHistorico);
                historicoExecucaoJobService.save(historicoExecucaoJob);
            }
        }
        catch (Exception e){
            LOGGER.error(e.getStackTrace().toString());
            jobExecution.setStatus(BatchStatus.FAILED);
            throw new StepListenerFailedException("Erro ao finalizar o job", e);
        }
    }


    /**
     * This method is responsible to recovery a {@link HistoricoExecucaoJob} from database and updates the object
     * with the finishing status from the job instance run.
     * @param jobExecution {@link JobExecution} with the metadata from the job instance run.
     * @param idHistorico {@link Long} representing the id from {@link HistoricoExecucaoJob}
     * @return an entity with the updated finishing status from the job.
     */
    private HistoricoExecucaoJob getHistoricoExecution(JobExecution jobExecution, Long idHistorico) {
        HistoricoExecucaoJob historicoExecucaoJob = historicoExecucaoJobService.findById(idHistorico);
        Date endTime = jobExecution.getEndTime();
        LocalDateTime endTimeLocalDateTime = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        historicoExecucaoJob.setDataFimExecucao(endTimeLocalDateTime);

        if(!historicoExecucaoJob.isProcessamentoCompleto()) {
            String descricaoErro = jobExecution.getStepExecutions().stream()
                    .filter(stepExecution ->
                            stepExecution.getExitStatus().getExitCode().equals(ExitStatus.FAILED.getExitCode())
                    )
                    .map(stepExecution ->
                            stepExecution.getExitStatus().getExitDescription()
                    )
                    .findFirst().orElse(StringUtils.EMPTY);

            if(descricaoErro.length() > 250){
                historicoExecucaoJob.setErroExecucao(descricaoErro.substring(0, 250));
            }
            else {
                historicoExecucaoJob.setErroExecucao(descricaoErro);
            }
        }

        return historicoExecucaoJob;
    }
}
