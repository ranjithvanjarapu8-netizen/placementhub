package com.placementhub.service;

import com.placementhub.dto.SupersetStudentDto;
import com.placementhub.entity.Student;
import com.placementhub.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentSyncService {

    private final StudentRepository studentRepository;

    public StudentSyncService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student syncStudent(SupersetStudentDto dto) {

        // Find existing student using Superset UUID
        Student student = studentRepository
                .findBySupersetStudentId(dto.getUuid())
                .orElseGet(Student::new);

        // Map Superset data
        student.setSupersetStudentId(dto.getUuid());

        student.setName(dto.getFullName());

        student.setEmail(dto.getEmail());

        // Batch
        if (dto.getInvitation() != null
                && dto.getInvitation().getBatch() != null) {

            student.setBatch(
                    dto.getInvitation()
                            .getBatch()
                            .getName()
            );
        }

        // CGPA
        if (dto.getInvitation() != null
                && dto.getInvitation().getCurrentCourseScoreValue() != null) {

            try {
                student.setCgpa(
                        Double.parseDouble(
                                dto.getInvitation()
                                        .getCurrentCourseScoreValue()
                        )
                );
            } catch (NumberFormatException e) {
                student.setCgpa(null);
            }
        }

        /*
         * Branch is not available as a clean field
         * in the current Superset student response.
         */
        student.setBranch(null);

        /*
         * Password is a local PlacementHub field.
         * We do not overwrite it during Superset synchronization.
         *
         * For a newly created student, we temporarily give
         * an unusable/default value. Later, authentication
         * will be handled separately.
         */
        if (student.getPassword() == null) {
            student.setPassword("SUPerset_SYNC_USER");
        }

        return studentRepository.save(student);
    }
}