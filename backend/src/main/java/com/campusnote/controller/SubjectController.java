package com.campusnote.controller;

import com.campusnote.entity.Subject;
import com.campusnote.service.SubjectService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(
            SubjectService subjectService) {

        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {

        return ResponseEntity.ok(
                subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                subjectService.getSubjectById(id));
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(
            @RequestBody Subject subject) {

        return ResponseEntity.ok(
                subjectService.createSubject(subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(
            @PathVariable Integer id,
            @RequestBody Subject subject) {

        return ResponseEntity.ok(
                subjectService.updateSubject(id, subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(
            @PathVariable Integer id) {

        subjectService.deleteSubject(id);

        return ResponseEntity.noContent().build();
    }
}