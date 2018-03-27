package business;

import data_access.ExamRepository;
import data_access.dto.Enrollment;
import data_access.dto.Exam;

class ExamBLL {
    private final ExamRepository examRepository;

    ExamBLL(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam findExamFromEnrollment(Enrollment enrollment) {
        return examRepository.findExamFromEnrollment(enrollment);
    }

    public void addGrade(Exam exam) {
        examRepository.add(exam);
    }
}
