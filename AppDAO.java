package com.example.AppDAO;

import com.example.Data.*;

import java.util.List;

public interface AppDAO {
    public void save(Instructor instructor);

    public Instructor findInstructor(int id);
    public InstructorDetails findInstructorDetails(int id);

    public List<Course> findCoursesById(int id);
    public Instructor findInstructorByJoinFetch(int id);
    public void updateInstructor(Instructor instructor);
    public void updateCourse(Course course);
    public void deleteInstructor(int id);
    public void deleteCourse(int id);
    public void saveCourse(Course course);
    public Course findReviewById(int id);

    public Course findCourseAndStudentsByCourseId(int id);

    public void addMoreCourseToStudent(Student student);

    Course findCourseByCourseId(int id);
    Student findStudentAndCoursesByStudentId(int id);

}
