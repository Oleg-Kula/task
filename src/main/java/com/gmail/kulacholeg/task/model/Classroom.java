package com.gmail.kulacholeg.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "classrooms")
public class Classroom {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    @OneToOne(mappedBy = "classroom", fetch = FetchType.LAZY)
    @JsonIgnore
    private Subject subject;

    public Classroom(){}

    public Classroom(String number){
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
