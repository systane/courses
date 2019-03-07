package br.com.spring_batch.configurations;


import br.com.spring_batch.entities.Account;
import br.com.spring_batch.entities.Person;
import job.processors.AccountProcessor;
import job.readers.AccountRepositoryItemReader;
import job.step.listener.BaseStepListener;
import job.writers.CsvWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

//    private static final String SHEDULE_JOB_CRON = "jobLauncher.schedule.cron";

    private final Environment environment;
    private final DataSource dataSource;
    private final JobBuilderFactory jobFactory;
    private final StepBuilderFactory stepFactory;
    private final AccountRepositoryItemReader accountRepositoryItemReader;
    private final AccountProcessor accountProcessor;
    private final CsvWriter csvWriter;
    private final BaseStepListener baseStepListener;

    @Autowired
    public SpringBatchConfiguration(Environment environment, DataSource dataSource, JobBuilderFactory jobFactory, StepBuilderFactory stepFactory,
                                    AccountRepositoryItemReader accountRepositoryItemReader,
                                    AccountProcessor accountProcessor, CsvWriter csvWriter, BaseStepListener baseStepListener) {
        this.environment = environment;
        this.dataSource = dataSource;
        this.jobFactory = jobFactory;
        this.stepFactory = stepFactory;
        this.accountRepositoryItemReader = accountRepositoryItemReader;
        this.accountProcessor = accountProcessor;
        this.csvWriter = csvWriter;
        this.baseStepListener = baseStepListener;
    }

    @Bean
    protected Step step1(AccountRepositoryItemReader reader, AccountProcessor processor, CsvWriter writer, BaseStepListener listener){
        return stepFactory.get("step1")
                .listener(listener)
                .<Account, Person>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobFactory, @Qualifier("step1") Step step) {
        return jobFactory.get("jobTest")
                .flow(step)
                .end()
                .build();
    }

}
