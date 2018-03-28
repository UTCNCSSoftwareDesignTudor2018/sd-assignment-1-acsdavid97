package data_access;

import data_access.dto.Exam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

class ExamDatabaseTest {

    private ExamDatabase examDatabase;
    @BeforeEach
    void setUp() {
        this.examDatabase = new ExamDatabase();
    }

    @Test
    void testAdd() {
        Exam exam = new Exam(0, 10, 2, new Timestamp(System.currentTimeMillis()));
        this.examDatabase.add(exam);
    }


}