package data_access;

import data_access.dto.Student;

public class StudentRepository extends DatabaseRepository<Student> {
    StudentRepository() {
        super(Student.class);
    }
}
