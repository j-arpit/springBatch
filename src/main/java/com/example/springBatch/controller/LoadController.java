package com.example.springBatch.controller;

//importing exceptions
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.JobParametersInvalidException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.batch.core.BatchStatus;

@RestController
@RequestMapping("/load")
public class LoadController {
    
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @GetMapping
    public BatchStatus load() throws JobExecutionAlreadyRunningException, IllegalArgumentException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
        JobParameters jobParameters = new JobParameters();
        JobExecution jobExecution =  jobLauncher.run(job, jobParameters);
        System.out.println("JobExecution: " + jobExecution.getStatus());

        System.out.println("Batch is running");
        while(jobExecution.isRunning()) {
            System.out.println(">>>");
        }
        System.out.println("Done");

        return jobExecution.getStatus();
    }
}
