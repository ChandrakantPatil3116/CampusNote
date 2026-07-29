package com.campusnote.repository;

import com.campusnote.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByNoteNoteId(Integer noteId);
}