package com.test2;

import java.util.List;

/**
 * Created by yuchen.wu on 2020-11-15
 */

public class School {

    private String name;
    private List<Klass> classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Klass> getClasses() {
        return classes;
    }

    public void setClasses(List<Klass> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "School{" + "name='" + name + '\'' + ", classes=" + classes + '}';
    }
}
