package com.campusnote.service;

import java.util.List;

import com.campusnote.dto.NoteRequest;
import com.campusnote.dto.NoteResponse;

public interface NoteService {

    NoteResponse createNote(NoteRequest request);

    List<NoteResponse> getAllNotes();

    NoteResponse getNoteById(Integer noteId);

    List<NoteResponse> getNotesBySubject(Integer subjectId);

    List<NoteResponse> getNotesByUser(Integer userId);

    List<NoteResponse> searchNotes(String keyword);

    NoteResponse updateNote(
            Integer noteId,
            NoteRequest request
    );

    void deleteNote(Integer noteId);
}