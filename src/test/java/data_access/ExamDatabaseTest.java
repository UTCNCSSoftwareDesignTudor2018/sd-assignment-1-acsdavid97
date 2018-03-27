package data_access;

import data_access.dto.Exam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

class ExamRepositoryTest {

    private ExamRepository examRepository;
    @BeforeEach
    void setUp() {
        this.examRepository = new ExamRepository();
    }

    @Test
    void testAdd() {
        Exam exam = new Exam(0, 10, 2, new Timestamp(System.currentTimeMillis()));
        this.examRepository.add(exam);
    }


}