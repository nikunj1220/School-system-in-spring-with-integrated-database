//package com.example.Data;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "course_student")
//public class Course_Student {
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "course_id")
//    public Course course;
//
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_id")
//    public Student student;
//
//    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }
//
//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//
//    public Course_Student() {
//    }
//
//}
