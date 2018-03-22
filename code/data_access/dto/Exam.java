package data_access.dto;

import java.util.Objects;

public class Exam implements GenericDTO {
    private int id;
    private int grade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return id == exam.id &&
                grade == exam.grade;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, grade);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", grade=" + grade +
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

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
