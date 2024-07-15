package com.example.AppDAO;

import com.example.Data.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    private EntityManager entityManager;
    @Autowired
    public AppDAOImpl(EntityManager entityManager)  {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }
    @Override
    public Instructor findInstructor(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public InstructorDetails findInstructorDetails(int id) {
        return entityManager.find(InstructorDetails.class, id);
    }

    @Override
    public List<Course> findCoursesById(int id) { // using this we have to setCourses in integer using setCourses() in order to use fetch courses via instructor
        TypedQuery<Course> courseTypedQuery = entityManager.createQuery("from Course Where instructor.id = :data", Course.class);
        courseTypedQuery.setParameter("data", id);

        List<Course> courseList = courseTypedQuery.getResultList();
        return courseList;
    }

    @Override
    public Instructor findInstructorByJoinFetch(int id) { // using this we do not have to setCourses separately in Instructor
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i Join FETCH i.courses where i.id = :data", Instructor.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {
        Instructor instructor = findInstructor(id);
        System.out.println("Deleted instructor: " + instructor);
        List<Course> courses = instructor.getCourses();
        courses.forEach(course -> course.setInstructor(null));
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {

        entityManager.remove(entityManager.find(Course.class,id));
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {

        entityManager.persist(course);
    }

    @Override
    public Course findReviewById(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "Join FETCH c.reviews " +
                        "where c.id = :data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c from Course c "
                        + "JOIN FETCH c.students"
                        + " where c.id = :data ", Course.class);

        query.setParameter("data", id);
        return query.getSingleResult();
//        return entityManager.find(Course.class, id);

    }
    @Override
    @Transactional
    public void addMoreCourseToStudent(Student student) {
        entityManager.merge(student);
    }

    @Override
    public Course findCourseByCourseId(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s from Student s "
                        + "JOIN FETCH s.courses"
                        + " where s.id = :data ", Student.class);

        query.setParameter("data", id);
        return query.getSingleResult();
    }
}
