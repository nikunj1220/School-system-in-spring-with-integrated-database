package com.example.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_details")
public class InstructorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "instructorDetails", cascade = CascadeType.ALL)
    private Instructor instructor;

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetails(String email) {
        this.email = email;
    }

    public InstructorDetails() {
    }

    @Override
    public String toString() {
        return "InstructorDetails{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }


}
