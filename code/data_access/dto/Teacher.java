package data_access.dto;

import java.util.Objects;

public class Teacher implements GenericDTO{
    private int id;
    private int user_id;

    public Teacher(int id, int user_id) {
        this.id = id;
        this.user_id = user_id;
    }

    public Teacher(Teacher teacher) {
        this.id = teacher.id;
        this.user_id = teacher.user_id;
    }

    public Teacher() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id &&
                user_id == teacher.user_id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user_id);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", user_id=" + user_id +
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
}
