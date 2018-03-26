package business;

import data_access.GenericRepository;
import data_access.dto.Course;
import data_access.CourseRepository;

import java.util.Collection;

public class CourseBLL {
    private GenericRepository<Course> courseRepository;

    CourseBLL() {
        this.courseRepository = new CourseRepository();
    }

    public Collection<Course> getCourses() {
        return courseRepository.getAll();
    }

    public Course findCourseById(int course_id) {
        return courseRepository.findById(course_id);
    }
}
