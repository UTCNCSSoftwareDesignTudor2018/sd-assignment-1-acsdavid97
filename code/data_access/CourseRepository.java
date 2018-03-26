package data_access.dto;

import data_access.DatabaseRepository;

public class CourseRepository extends DatabaseRepository<Course>{
    public CourseRepository() {
        super(Course.class);
    }
}
