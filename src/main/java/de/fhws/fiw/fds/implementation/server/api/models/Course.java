package de.fhws.fiw.fds.implementation.server.api.models;

import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

public class Course extends AbstractModel {

    private String Name;

    public Course(String name) {
        Name = name;
    }

    public Course() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Name='" + Name + '\'' +
                ", id=" + id +
                '}';
    }
}
