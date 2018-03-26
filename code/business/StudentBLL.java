package business;

import data_access.GenericRepository;
import data_access.StudentRepository;
import data_access.dto.Student;
import data_access.dto.User;

public class StudentBLL {

    private StudentRepository studentRepository;

    public StudentBLL() {
        this.studentRepository = new StudentRepository();
    }

    public Student findStudentByUser(User user) {
        return studentRepository.findStudentByUser(user);
    }

    public Student findStudentById(int id) {
        return studentRepository.findById(id);
    }
}
