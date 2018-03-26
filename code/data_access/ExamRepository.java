package data_access;

import data_access.dto.Exam;

public class ExamRepository extends DatabaseRepository<Exam>{
    protected ExamRepository() {
        super(Exam.class);
    }
}
