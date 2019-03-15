package br.com.spring_batch.configurations;


import br.com.spring_batch.JobRunner;
import br.com.spring_batch.entities.Account;
import br.com.spring_batch.entities.Person;
import job.Listeners.job.BaseJobListener;
import job.Listeners.writer.CsvItemWriterListener;
import job.processors.AccountProcessor;
import job.readers.AccountRepositoryItemReader;
import job.writers.CsvWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfiguration {

    private final JobBuilderFactory jobFactory;
    private final StepBuilderFactory stepFactory;
    private final AccountRepositoryItemReader accountRepositoryItemReader;
    private final AccountProcessor accountProcessor;
    private final CsvWriter csvWriter;
    private final BaseJobListener baseJobListener;
    private final CsvItemWriterListener csvItemWriterListener;

//    @Bean
//    public JobParameters jobParameters(){
//        return new JobParametersBuilder().toJobParameters();
//    }

//    @Bean
//    public StepScope stepScope() {
//        final StepScope stepScope = new StepScope();
//        stepScope.setAutoProxy(true);
//        return stepScope;
//    }

    @Bean
    /**
     * Step 1: This step should read data from database and save to a CSV file
     */
    protected Step step1(AccountRepositoryItemReader reader, AccountProcessor processor, CsvWriter writer,
                         CsvItemWriterListener writerListener){
        return stepFactory.get("step1")
            .<Account, Person>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(writerListener)
            .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobFactory, @Qualifier(JobRunner.STEP1) Step step, BaseJobListener jobListener) {
        return jobFactory.get("jobTest")
                .flow(step)
                .end()
                .listener(jobListener)
                .build();
    }

}
