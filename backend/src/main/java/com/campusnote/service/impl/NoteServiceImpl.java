package com.campusnote.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusnote.dto.NoteRequest;
import com.campusnote.dto.NoteResponse;
import com.campusnote.entity.Note;
import com.campusnote.entity.Subject;
import com.campusnote.entity.User;
import com.campusnote.exception.ResourceNotFoundException;
import com.campusnote.repository.NoteRepository;
import com.campusnote.repository.SubjectRepository;
import com.campusnote.repository.UserRepository;
import com.campusnote.service.NoteService;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public NoteServiceImpl(
            NoteRepository noteRepository,
            SubjectRepository subjectRepository,
            UserRepository userRepository) {

        this.noteRepository = noteRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NoteResponse createNote(NoteRequest request) {

        Subject subject = subjectRepository
                .findById(request.getSubjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subject not found with id: "
                                        + request.getSubjectId()
                        )
                );

        User user = userRepository
                .findById(request.getUploadedById())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: "
                                        + request.getUploadedById()
                        )
                );

        Note note = new Note();

        note.setTitle(request.getTitle());
        note.setDescription(request.getDescription());
        note.setFilePath(request.getFilePath());
        note.setSubject(subject);
        note.setUploadedBy(user);
        note.setUploadDate(LocalDateTime.now());
        note.setDownloads(0);

        Note savedNote = noteRepository.save(note);

        return mapToResponse(savedNote);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponse> getAllNotes() {

        return noteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public NoteResponse getNoteById(Integer noteId) {

        Note note = noteRepository
                .findById(noteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Note not found with id: "
                                        + noteId
                        )
                );

        return mapToResponse(note);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponse> getNotesBySubject(
            Integer subjectId) {

        Subject subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subject not found with id: "
                                        + subjectId
                        )
                );

        return noteRepository
                .findBySubject(subject)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponse> getNotesByUser(
            Integer userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: "
                                        + userId
                        )
                );

        return noteRepository
                .findByUploadedBy(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponse> searchNotes(
            String keyword) {

        return noteRepository
                .findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public NoteResponse updateNote(
            Integer noteId,
            NoteRequest request) {

        Note note = noteRepository
                .findById(noteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Note not found with id: "
                                        + noteId
                        )
                );

        Subject subject = subjectRepository
                .findById(request.getSubjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subject not found with id: "
                                        + request.getSubjectId()
                        )
                );

        note.setTitle(request.getTitle());
        note.setDescription(request.getDescription());
        note.setFilePath(request.getFilePath());
        note.setSubject(subject);

        Note updatedNote =
                noteRepository.save(note);

        return mapToResponse(updatedNote);
    }

    @Override
    public void deleteNote(Integer noteId) {

        Note note = noteRepository
                .findById(noteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Note not found with id: "
                                        + noteId
                        )
                );

        noteRepository.delete(note);
    }

    private NoteResponse mapToResponse(Note note) {

        NoteResponse response = new NoteResponse();

        response.setNoteId(note.getNoteId());
        response.setTitle(note.getTitle());
        response.setDescription(note.getDescription());
        response.setFilePath(note.getFilePath());
        response.setUploadDate(note.getUploadDate());
        response.setDownloads(note.getDownloads());

        if (note.getSubject() != null) {

            response.setSubjectId(
                    note.getSubject().getSubjectId()
            );

            response.setSubjectName(
                    note.getSubject().getSubjectName()
            );
        }

        if (note.getUploadedBy() != null) {

            response.setUploadedById(
                    note.getUploadedBy().getUserId()
            );

            response.setUploadedByName(
                    note.getUploadedBy().getName()
            );
        }

        return response;
    }
}