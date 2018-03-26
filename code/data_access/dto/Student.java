package data_access.dto;

import java.util.Objects;

public class Student implements GenericDTO{
    private int id;
    private int user_id;

    public Student(int id, int user_id, String id_number) {
        this.id = id;
        this.user_id = user_id;
        this.id_number = id_number;
    }

    public Student(Student student) {
        this.id = student.id;
        this.user_id = student.user_id;
        this.id_number = student.id_number;
    }

    public Student() {

    }

    private String id_number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                user_id == student.user_id &&
                Objects.equals(id_number, student.id_number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user_id, id_number);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", id_number='" + id_number + '\'' +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }
}
