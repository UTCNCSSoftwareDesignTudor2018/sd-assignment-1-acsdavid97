package data_access.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Exam implements GenericDTO {
    private int id;
    private int grade;
    private int enrollment_id;
    private Timestamp date;

    public Exam(int id, int grade, int enrollment_id, Timestamp date) {
        this.id = id;
        this.grade = grade;
        this.enrollment_id = enrollment_id;
        this.date = date;
    }

    public Exam(Exam exam) {
        this.id = exam.id;
        this.grade = exam.grade;
        this.enrollment_id = exam.enrollment_id;
        this.date = exam.date;
    }

    public Exam() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return id == exam.id &&
                grade == exam.grade &&
                enrollment_id == exam.enrollment_id &&
                date.equals(exam.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, grade, enrollment_id, date);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", grade=" + grade +
                ", enrollment_id=" + enrollment_id +
                ", date=" + date +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public int getEnrollment_id() {
        return enrollment_id;
    }

    public void setEnrollment_id(int enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
