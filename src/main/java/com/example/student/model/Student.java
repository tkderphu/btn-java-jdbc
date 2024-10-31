package com.example.student.model;

public class Student {
    private String id;
    private String name;
    private String classId;
    private Float gpa;

    public Student(String id, String name, String classId, Float gpa) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassId() {
        return classId;
    }

    public Float getGpa() {
        return gpa;
    }
}
