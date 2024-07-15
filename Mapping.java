package com.example;

import com.example.AppDAO.AppDAO;
//import com.example.Data.Course;
import com.example.Data.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Mapping {

	public static void main(String[] args) {

		SpringApplication.run(Mapping.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			findInstructor(appDAO);
//			findInstructorDetailsWithCourses(appDAO);
//			createInstructorWithCourses(appDAO);
//			updateInstructor(appDAO);
//			updateCourse(appDAO);
//			deleteInstructor(appDAO);
//			deleteCourse(appDAO);
//			createReview(appDAO);
//			findReview(appDAO);
//			saveStudent(appDAO);
//			findCourseAndStudents(appDAO);
			addMoreCourseToStudent(appDAO);
		};
	}


	private void createInstructorWithCourses(AppDAO appDAO) {
		InstructorDetails tempInstructorDetails = new InstructorDetails("Nik@gmail.com");
		Instructor tempInstructor = new Instructor(2,"Nik","Gupta");
		Course tempCourse1 = new Course("OOPS");
		Course tempCourse2 = new Course("DSA");
		tempInstructor.setInstructorDetails(tempInstructorDetails);
		tempInstructorDetails.setInstructor(tempInstructor);
		tempInstructor.setInstructorDetails(tempInstructorDetails);
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		appDAO.save(tempInstructor);
	}

	private void findInstructorDetailsWithCourses(AppDAO appDAO) {
//		System.out.println(appDAO.findInstructorDetails(402));
//		System.out.println(appDAO.findInstructorDetails(402).getInstructor());
		Instructor instructor = appDAO.findInstructor(1);
		List<Course> course = appDAO.findCoursesById(1);
		instructor.setCourses(course);
		System.out.println("Using findCoursesById(): " + instructor.getCourses());

		Instructor instructor1 = appDAO.findInstructorByJoinFetch(1);
		System.out.println("Using findInstructorByJoinFetch(): " + instructor1.getCourses());
	}
	private void findInstructor(AppDAO appDAO){
		System.out.println("Finding instructor with id :"+ 1);
		System.out.println(appDAO.findInstructor(252));
		System.out.println(appDAO.findInstructor(252).getInstructorDetails());
	}

	private void updateInstructor(AppDAO appDAO) {
		Instructor instructor = appDAO.findInstructor(1);
		instructor.setFirst_name("Raj");
		instructor.setLast_name("Kapoor");
		appDAO.updateInstructor(instructor);
		System.out.println(appDAO.findInstructor(1));
	}

	private void deleteCourse(AppDAO appDAO) {
		appDAO.deleteCourse(53);
	}

	private void deleteInstructor(AppDAO appDAO) {
		appDAO.deleteInstructor(2);
	}

	private void updateCourse(AppDAO appDAO) {
		List<Course> course = appDAO.findCoursesById(1);
		course.forEach(z -> {
			if(z.getId() == 1) {
				z.setTitle("OOPS");
				appDAO.updateCourse(z);
			}
		});
	}
	private void createReview(AppDAO appDAO) {
		Review review = new Review("This is my first Review");
		Course tempCourse1 = new Course("C++");
		tempCourse1.addReview(review);
		appDAO.saveCourse(tempCourse1);
	}


	private void findReview(AppDAO appDAO) {
		Course course = appDAO.findReviewById(102);
		System.out.println(course);
		System.out.println(course.getReviews());
	}

	public void saveStudent(AppDAO appDAO) {
		Course course = new Course("Maths");
		Student student = new Student("Nikunj", "Gupta");
		course.addStudent(student);
		appDAO.saveCourse(course);
//		appDAO.saveCourseAndStudent(course, student);
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		Course course = appDAO.findCourseAndStudentsByCourseId(352);
		System.out.println("Course :" + course);
		System.out.println(course.getStudents());
	}


	private void addMoreCourseToStudent(AppDAO appDAO) {
		Course course = appDAO.findCourseByCourseId(102);
		Student student = appDAO.findStudentAndCoursesByStudentId(2);
		student.addCourse(course);
		appDAO.addMoreCourseToStudent(student);
	}
}
