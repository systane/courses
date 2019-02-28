package br.com.spring_batch.endpoints;

import br.com.spring_batch.services.AccountService;
import br.com.spring_batch.entities.Account;
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
import java.util.Objects;

@RestController
public class MainEndpoint {

    private final AccountService accountService;

    @Autowired
    public MainEndpoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/")
    public String home(){

        Account account = accountService.readAccount();

        if(Objects.nonNull(account)){
            System.out.println(account.toString());
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
