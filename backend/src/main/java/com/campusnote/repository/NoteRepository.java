package com.campusnote.repository;

import com.campusnote.entity.Note;
import com.campusnote.entity.Subject;
import com.campusnote.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findBySubject(Subject subject);

    List<Note> findByUploadedBy(User user);

    List<Note> findByTitleContainingIgnoreCase(String keyword);

}
