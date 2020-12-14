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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;

@RestController
@RequestMapping("/load")
public class LoadController {
    
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public BatchStatus load() throws JobExecutionAlreadyRunningException, IllegalArgumentException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
        long starttime = System.currentTimeMillis();
        JobParameters jobParameters = new JobParameters();
        JobExecution jobExecution =  jobLauncher.run(job, jobParameters);
        long endtime = System.currentTimeMillis();
        logger.info("Total Time taken by this batch" + (endtime-starttime));
        System.out.println("JobExecution: " + jobExecution.getStatus());
        return jobExecution.getStatus();
    }
}
