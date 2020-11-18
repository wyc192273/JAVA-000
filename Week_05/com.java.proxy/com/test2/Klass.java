package com.test2;

import java.util.List;

/**
 * Created by yuchen.wu on 2020-11-15
 */

public class Klass {

    private String name;
    private List<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Klass{" + "name='" + name + '\'' + ", students=" + students + '}';
    }
}
