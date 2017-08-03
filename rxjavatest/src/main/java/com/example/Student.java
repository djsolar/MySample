package com.example;

import java.util.List;

/**
 * Created by zhouyiran on 2017/7/21.
 */

public class Student {

    private String name;

    private List<Course> courses;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
