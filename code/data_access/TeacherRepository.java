package data_access;

import data_access.dto.Teacher;
import data_access.dto.User;

public interface TeacherRepositoryInterface extends GenericRepository<Teacher>{

   Teacher findTeacherByUser(User user) throws RepositoryException;
}
