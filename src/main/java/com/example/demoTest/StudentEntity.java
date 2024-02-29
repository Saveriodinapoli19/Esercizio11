package com.example.demoTest;

import jakarta.persistence.*;

@Table
@Entity
public class StudentEntity {
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private boolean Working;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isWorking() {
        return Working;
    }

    public void setWorking(boolean working) {
        Working = working;
    }

    public StudentEntity(Long id, String name, String surname, boolean working) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.Working = working;
    }

    public StudentEntity() {
    }
}
