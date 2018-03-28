package business.facade;

import business.facade.process.CourseBLL;
import data_access.CourseDatabase;
import data_access.dto.Course;
import data_access.dto.Login;
import data_access.dto.Teacher;

import java.util.Collection;

public class CourseFacade {
    private CourseBLL courseBLL = new CourseBLL(new CourseDatabase());

    private LoginFacade loginFacade = new LoginFacade();

    public void addCourse(String courseName, Teacher teacher) {
        Course course = new Course();
        course.setName(courseName);
        course.setTeacher_id(teacher.getId());
        courseBLL.addCourse(course);
    }

    public Collection<Course> getCourses() {
        return courseBLL.getCourses();
    }

    public Teacher findTeacherByUsername(String username) {
        Login login = loginFacade.findLoginByUsername(username);
        if (login == null) {
            return null;
        }
        return loginFacade.findTeacherByLogin(login);
    }

}
