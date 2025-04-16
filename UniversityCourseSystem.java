package generics;

import java.util.*;


abstract class CourseType {
 private String courseName;

 public CourseType(String courseName) {
     this.courseName = courseName;
 }

 public String getCourseName() {
     return courseName;
 }


 public abstract void showEvaluationMethod();
}


class ExamCourse extends CourseType {
 private int finalExamWeightage;

 public ExamCourse(String courseName, int finalExamWeightage) {
     super(courseName);
     this.finalExamWeightage = finalExamWeightage;
 }

 @Override
 public void showEvaluationMethod() {
     System.out.println("Exam Course: " + getCourseName() +
             " | Final Exam Weightage: " + finalExamWeightage + "%");
 }
}


class AssignmentCourse extends CourseType {
 private int numberOfAssignments;

 public AssignmentCourse(String courseName, int numberOfAssignments) {
     super(courseName);
     this.numberOfAssignments = numberOfAssignments;
 }

 @Override
 public void showEvaluationMethod() {
     System.out.println("Assignment Course: " + getCourseName() +
             " | Total Assignments: " + numberOfAssignments);
 }
}


class ResearchCourse extends CourseType {
 private String researchTopic;

 public ResearchCourse(String courseName, String researchTopic) {
     super(courseName);
     this.researchTopic = researchTopic;
 }

 @Override
 public void showEvaluationMethod() {
     System.out.println("Research Course: " + getCourseName() +
             " | Topic: " + researchTopic);
 }
}


class Course<T extends CourseType> {
 private List<T> courses = new ArrayList<>();

 public void addCourse(T course) {
     courses.add(course);
 }

 public T getCourse(int index) {
     return courses.get(index);
 }

 public List<T> getAllCourses() {
     return courses;
 }
}


class CourseUtils {
 public static void displayAllCourses(List<? extends CourseType> courses) {
     for (CourseType course : courses) {
         course.showEvaluationMethod();  
     }
 }
}


public class UniversityCourseSystem {
 public static void main(String[] args) {
 
     Course<ExamCourse> examDept = new Course<>();
     Course<AssignmentCourse> assignmentDept = new Course<>();
     Course<ResearchCourse> researchDept = new Course<>();

     
     examDept.addCourse(new ExamCourse("Mathematics", 70));
     examDept.addCourse(new ExamCourse("Physics", 60));

     assignmentDept.addCourse(new AssignmentCourse("Software Engineering", 5));
     assignmentDept.addCourse(new AssignmentCourse("Web Technologies", 4));

     researchDept.addCourse(new ResearchCourse("AI Research", "Deep Learning"));
     researchDept.addCourse(new ResearchCourse("Data Science", "Big Data Analytics"));


     System.out.println("    Exam-Based Courses    ");
     CourseUtils.displayAllCourses(examDept.getAllCourses());

     System.out.println("\n    Assignment-Based Courses    ");
     CourseUtils.displayAllCourses(assignmentDept.getAllCourses());

     System.out.println("\n    Research-Based Courses    ");
     CourseUtils.displayAllCourses(researchDept.getAllCourses());
 }
}



