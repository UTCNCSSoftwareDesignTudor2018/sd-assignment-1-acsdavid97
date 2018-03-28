package business.facade.process;

import data_access.CourseRepository;
import data_access.dto.Course;

import java.util.Collection;

public class CourseBLL {
    private final CourseRepository courseRepository;

    public CourseBLL(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Collection<Course> getCourses() {
        return courseRepository.getAll();
    }

    public Course findCourseById(int course_id) {
        return courseRepository.findById(course_id);
    }

    public void addCourse(Course course) {
        courseRepository.add(course);
    }
}
