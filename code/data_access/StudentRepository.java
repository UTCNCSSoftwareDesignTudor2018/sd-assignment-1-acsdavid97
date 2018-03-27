package data_access;

import data_access.dto.Student;
import data_access.dto.User;

public interface StudentRepository extends GenericRepository<Student>{

    Student findStudentByUser(User user) throws RepositoryException;
}
