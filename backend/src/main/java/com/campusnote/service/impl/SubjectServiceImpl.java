package com.campusnote.service.impl;

import com.campusnote.entity.Subject;
import com.campusnote.exception.ResourceNotFoundException;
import com.campusnote.repository.SubjectRepository;
import com.campusnote.service.SubjectService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(
            SubjectRepository subjectRepository) {

        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Integer id) {

        return subjectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subject not found"));
    }

    @Override
    public Subject updateSubject(
            Integer id,
            Subject subject) {

        Subject existing =
                getSubjectById(id);

        existing.setSubjectName(
                subject.getSubjectName());

        existing.setDepartment(
                subject.getDepartment());

        existing.setSemester(
                subject.getSemester());

        return subjectRepository.save(existing);
    }

    @Override
    public void deleteSubject(Integer id) {

        Subject subject =
                getSubjectById(id);

        subjectRepository.delete(subject);
    }
}