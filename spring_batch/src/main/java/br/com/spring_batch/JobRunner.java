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
    private final Environment environment;

    @Autowired
    public JobRunner(JobLauncher jobLauncher, Job job, Environment environment) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.environment = environment;
    }

    @Scheduled(cron = "0 33 17 ? * *")
    public void schedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
    }
}
