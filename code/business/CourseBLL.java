package business;

import data_access.CourseRepository;
import data_access.GenericRepository;
import data_access.dto.Course;
import data_access.CourseDatabase;

import java.util.Collection;

public class CourseBLL {
    private CourseRepository courseRepository;

    CourseBLL(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Collection<Course> getCourses() {
        return courseRepository.getAll();
    }

    public Course findCourseById(int course_id) {
        return courseRepository.findById(course_id);
    }
}
