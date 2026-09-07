package com.campusnote.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusnote.dto.NoteRequest;
import com.campusnote.dto.NoteResponse;
import com.campusnote.service.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(
            @Valid @RequestBody NoteRequest request) {

        NoteResponse response =
                noteService.createNote(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes() {

        return ResponseEntity.ok(
                noteService.getAllNotes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                noteService.getNoteById(id)
        );
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<NoteResponse>>
    getNotesBySubject(
            @PathVariable Integer subjectId) {

        return ResponseEntity.ok(
                noteService.getNotesBySubject(
                        subjectId
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NoteResponse>>
    getNotesByUser(
            @PathVariable Integer userId) {

        return ResponseEntity.ok(
                noteService.getNotesByUser(
                        userId
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<NoteResponse>>
    searchNotes(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                noteService.searchNotes(keyword)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable Integer id,
            @Valid @RequestBody NoteRequest request) {

        return ResponseEntity.ok(
                noteService.updateNote(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable Integer id) {

        noteService.deleteNote(id);

        return ResponseEntity.noContent().build();
    }
}