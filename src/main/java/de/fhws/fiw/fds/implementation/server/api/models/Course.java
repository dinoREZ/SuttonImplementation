package de.fhws.fiw.fds.implementation.server.api.models;

import com.owlike.genson.annotation.JsonConverter;
import de.fhws.fiw.fds.sutton.server.api.converter.JsonServerLinkConverter;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;

public class Course extends AbstractModel {

    private String Name;

    public Course(String name) {
        Name = name;
    }

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "/students/${instance.primaryId}/courses/${instance.id}",
            rel = "self",
            title = "self",
            condition = "${instance.primaryId != 0}"
    )
    private Link selfLinkOnSecond;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "/courses/${instance.id}",
            rel = "self",
            title = "self",
            condition = "${instance.primaryId == 0}"
    )
    private Link selfLinkPrimary;

    @InjectLink(
            style = InjectLink.Style.ABSOLUTE,
            value = "courses/${instance.id}/students",
            rel = "getStudentsOfCourse",
            title = "students"
    )
    private Link students;

    public Course() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
    public Link getStudents() {
        return students;
    }

    public void setStudents(Link students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Name='" + Name + '\'' +
                ", id=" + id +
                '}';
    }
}
