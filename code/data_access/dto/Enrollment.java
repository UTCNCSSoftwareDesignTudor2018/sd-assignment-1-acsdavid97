package data_access.dto;

import java.util.Objects;

public class Enrollment implements GenericDTO{
    private int id;
    private int student_id;
    private int course_id;

    public Enrollment(int id, int student_id, int course_id) {
        this.id = id;
        this.student_id = student_id;
        this.course_id = course_id;
    }

    public Enrollment(Enrollment enrollment) {
        this.id = enrollment.id;
        this.student_id = enrollment.student_id;
        this.course_id = enrollment.course_id;
    }

    public Enrollment() {
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", course_id=" + course_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return id == that.id &&
                student_id == that.student_id &&
                course_id == that.course_id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, student_id, course_id);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
