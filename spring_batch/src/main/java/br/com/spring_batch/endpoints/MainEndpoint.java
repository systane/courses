package br.com.spring_batch.endpoints;

import br.com.spring_batch.entities.Account;
import br.com.spring_batch.repositories.AccountRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MainEndpoint {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/")
    public String home(){

        List<Account> accounts = accountRepository.readAccount();

        if(!CollectionUtils.isEmpty(accounts)){
            accounts.stream().forEach(account -> System.out.println(account.toString()));
        }
        else {
            System.out.println("nothing fetched");
        }


        return "Batch job has been started";
    }

    @RequestMapping("/job")
    public void startJob(){
        String[] springConfig = {"/spring/jobs/job-extract-users.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("testJob");

        try {
            JobParameters params = new JobParametersBuilder().addString("email", "@hotmail").toJobParameters();

            JobExecution execution = jobLauncher.run(job, params);
            System.out.println("Exit status: "+ execution.getStatus());

        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        }
    }
}
