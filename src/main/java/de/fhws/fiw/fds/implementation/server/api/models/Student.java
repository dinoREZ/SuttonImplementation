package de.fhws.fiw.fds.implementation.server.api.models;

import com.owlike.genson.annotation.JsonConverter;
import de.fhws.fiw.fds.sutton.server.api.converter.JsonServerLinkConverter;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;

public class Student extends AbstractModel {
    private String firstName;
    private String lastName;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "/courses/${instance.primaryId}/students/${instance.id}",
            rel = "self",
            title = "self",
            condition = "${instance.primaryId != 0}"
    )
    private Link selfLinkOnSecond;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "/students/${instance.id}",
            rel = "self",
            title = "self",
            condition = "${instance.primaryId == 0}"
    )
    private Link selfLinkPrimary;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "students/${instance.id}/courses",
            rel = "getCoursesOfStudent",
            title = "courses"
    )
    private Link courses;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonConverter(JsonServerLinkConverter.class)
    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLinkOnSecond(Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }

    @JsonConverter(JsonServerLinkConverter.class)
    public Link getSelfLinkPrimary() {
        return selfLinkPrimary;
    }

    public void setSelfLinkPrimary(Link selfLinkPrimary) {
        this.selfLinkPrimary = selfLinkPrimary;
    }

    @JsonConverter(JsonServerLinkConverter.class)
    public Link getCourses() {
        return courses;
    }

    public void setCourses(Link courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }
}
