package com.brijesh.firstjobapp.job;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        if (jobService.deleteJobById(id) == true){
            return new ResponseEntity<>("Job deleted successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

/*

GET /jobs : Get all jobs
GET /jobs/{id} : Get a Specific job by id
POST /jobs : Create a new job (request body should contain the job details)
DELETE /jobs/{id} : Delete a specific job by id
PUT /jobs/{id} : Update a specific job by id (request body should contain the updated job details)
GET /jobs/{id}/company : Get the company associated with a specific job by id

Example API urls:

GET {base_url}/jobs
GET {base_url}/jobs/1
POST {base_url}/jobs/
DELETE {base_url}/jobs/1
PUT {base_url}/jobs/1
GET {base_url}/jobs/1/company

 */





















