package data_access.dto;

import java.util.Objects;

public class Course implements GenericDTO{
    private int id;
    private String name;
    private int teacher_id;

    public Course() {
    }

    public Course(int id, String name, int teacher_id) {
        this.id = id;
        this.name = name;
        this.teacher_id = teacher_id;
    }

    public Course(Course course) {
        this.id = course.id;
        this.name = course.name;
        this.teacher_id = course.teacher_id;
    }


    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id &&
                teacher_id == course.teacher_id &&
                Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, teacher_id);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher_id=" + teacher_id +
                '}';
    }
}
