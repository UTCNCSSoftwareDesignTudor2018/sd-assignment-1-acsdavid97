package data_access;

import data_access.dto.Course;

public class CourseDatabase extends DatabaseRepository<Course> implements CourseRepository {
    public CourseDatabase() {
        super(Course.class);
    }
}
