package com.campusnote.service;

import com.campusnote.entity.Subject;

import java.util.List;

public interface SubjectService {

    Subject createSubject(Subject subject);

    List<Subject> getAllSubjects();

    Subject getSubjectById(Integer id);

    Subject updateSubject(Integer id, Subject subject);

    void deleteSubject(Integer id);
}