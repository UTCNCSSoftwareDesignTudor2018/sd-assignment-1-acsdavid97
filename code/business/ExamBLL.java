package business;

import data_access.ExamDatabase;
import data_access.ExamRepository;
import data_access.dto.Enrollment;
import data_access.dto.Exam;

public class ExamBLL {
    private ExamRepository examRepository;

    ExamBLL(ExamRepository examRepository) {
        examRepository = examRepository;
    }

    public Exam findExamFromEnrollment(Enrollment enrollment) {
        return examRepository.findExamFromEnrollment(enrollment);
    }

    public void addGrade(Exam exam) {
        examRepository.add(exam);
    }
}
