package data_access;

import data_access.DatabaseRepository;
import data_access.dto.Course;

public class CourseRepository extends DatabaseRepository<Course> implements CourseRepositoryInterface{
    public CourseRepository() {
        super(Course.class);
    }
}
