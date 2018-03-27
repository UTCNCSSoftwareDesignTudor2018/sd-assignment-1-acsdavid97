package business;

import data_access.TeacherDatabase;
import data_access.TeacherRepository;
import data_access.dto.Teacher;
import data_access.dto.User;

import java.util.Collection;

public class TeacherBLL {
    private TeacherRepository teacherRepository;

    TeacherBLL(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher findTeacherByUser(User user) {
        return teacherRepository.findTeacherByUser(user);
    }

    public Collection<Teacher> getTeachers() {
        return teacherRepository.getAll();
    }

    public void updateTeacher(Teacher newTeacher) {
        teacherRepository.update(newTeacher);
    }

    public void addTeacher(Teacher newTeacher) {
        teacherRepository.add(newTeacher);
    }
}
