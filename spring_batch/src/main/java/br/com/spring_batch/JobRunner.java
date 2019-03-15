package br.com.spring_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {

    private final JobLauncher jobLauncher;
    private final Job job;
//    private final JobParameters jobParameters;
    private final Environment environment;
    public static final String PARAMETRO_ID_HISTORICO = "idHistorico";
    public static final String STEP1 = "step1";

//    @Bean
//    public JobParameters jobParameters(){
//        return new JobParametersBuilder().toJobParameters();
//    }


    @Autowired
    public JobRunner(JobLauncher jobLauncher, Job job, Environment environment) {
        this.jobLauncher = jobLauncher;
        this.job = job;
//        this.jobParameters = jobParameters;
        this.environment = environment;
    }

    /**
     * spring cron expressions:
     * They're build of 6 fields, and each field represents: s, min, h, day of month, month, day(s) of week
     * Possible values:
     *  1) * --> means match any
     *  2) *\/X --> means "every X"
     *  3) ? --> means "no specific value". E.g. If I want my trigger to fire on a particular
     *  day of the month (10th), but I don't care what day of the week that happens to be, I would put "10"
     *  in the day of month field and "?" in the day(s) of week field.
     */
    @Scheduled(cron = "0 06 14 * * ?")
    public void schedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
    }
}
