package com.campusnote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusnote.entity.Note;
import com.campusnote.entity.Subject;
import com.campusnote.entity.User;

public interface NoteRepository
        extends JpaRepository<Note, Integer> {

    List<Note> findBySubject(Subject subject);

    List<Note> findByUploadedBy(User user);

    List<Note> findByTitleContainingIgnoreCase(String keyword);
}