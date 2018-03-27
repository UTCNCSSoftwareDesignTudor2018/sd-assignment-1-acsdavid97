package business;

import data_access.GenericRepository;
import data_access.StudentRepository;
import data_access.dto.Student;
import data_access.dto.User;

import java.util.Collection;

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

    public Collection<Student> getStudents() {
        return studentRepository.getAll();
    }

    public void addStudent(Student newStudent) {
        studentRepository.add(newStudent);
    }

    public void updateStudent(Student newStudent) {
        studentRepository.update(newStudent);
    }
}
