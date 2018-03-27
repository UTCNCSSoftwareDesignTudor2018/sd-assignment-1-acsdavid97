package data_access;

import data_access.dto.Enrollment;
import data_access.dto.Exam;

public interface ExamRepository extends GenericRepository<Exam>{

    Exam findExamFromEnrollment(Enrollment enrollment) throws RepositoryException;
}
