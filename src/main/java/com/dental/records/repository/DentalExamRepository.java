package com.dental.records.repository;



import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.DentalExam;

public interface DentalExamRepository extends JpaRepository<DentalExam, Long>{
    List<DentalExam> findByDentistId(Long dentistId);
    DentalExam findByExamId(Long examId);
    List<DentalExam> findByFirstNameAndLastName(String firstName, String lastName);
    List<DentalExam> findByCompletionStatus(String completionStatus);
    List<DentalExam> findByUnitAssignAndPurposeDateBetween(String unitAssign, Date startDate, Date endDate);
    List<DentalExam> findByPurpose(String purpose);

}
