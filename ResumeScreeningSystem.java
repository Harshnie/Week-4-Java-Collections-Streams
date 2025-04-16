package generics;

import java.util.*;

abstract class JobRole {
 protected String candidateName;

 public JobRole(String candidateName) {
     this.candidateName = candidateName;
 }

 public abstract void evaluateResume();
 public String getCandidateName() {
     return candidateName;
 }
}


class SoftwareEngineer extends JobRole {
 public SoftwareEngineer(String name) {
     super(name);
 }

 public void evaluateResume() {
     System.out.println("Evaluating Software Engineer resume for: " + candidateName);
 }
}

class DataScientist extends JobRole { 
 public DataScientist(String name) {
     super(name);
 }

 public void evaluateResume() {
     System.out.println("Evaluating Data Scientist resume for: " + candidateName);
 }
}

class ProductManager extends JobRole {
 public ProductManager(String name) {
     super(name);
 }

 public void evaluateResume() {
     System.out.println("Evaluating Product Manager resume for: " + candidateName);
 }
}

class Resume<T extends JobRole> {
 private T jobApplicant;

 public Resume(T jobApplicant) {
     this.jobApplicant = jobApplicant;
 }

 public void process() {
     jobApplicant.evaluateResume();
 }

 public T getJobApplicant() {
     return jobApplicant;
 }
}

class ResumeScreeningPipeline {
 public static void screenResumes(List<? extends JobRole> applicants) {
     for (JobRole applicant : applicants) {
         applicant.evaluateResume();
     }
 }
}


public class ResumeScreeningSystem {
 public static void main(String[] args) {
     Resume<SoftwareEngineer> seResume = new Resume<>(new SoftwareEngineer("Alice"));
     Resume<DataScientist> dsResume = new Resume<>(new DataScientist("Bob"));
     Resume<ProductManager> pmResume = new Resume<>(new ProductManager("Charlie"));

     seResume.process();
     dsResume.process();
     pmResume.process();

     List<JobRole> allApplicants = new ArrayList<>();
     allApplicants.add(new SoftwareEngineer("David"));
     allApplicants.add(new DataScientist("Emma"));
     allApplicants.add(new ProductManager("Frank"));

     System.out.println("\n   Screening All Resumes   ");
     ResumeScreeningPipeline.screenResumes(allApplicants);
 }
}

