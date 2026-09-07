package com.campusnote.controller;

import com.campusnote.dto.NoteRequest;
import com.campusnote.dto.NoteResponse;
import com.campusnote.service.NoteService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @PathVariable Long id) {

        return ResponseEntity.ok(
                noteService.getNoteById(id)
        );
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<NoteResponse>>
    getNotesBySubject(
            @PathVariable Long subjectId) {

        return ResponseEntity.ok(
                noteService.getNotesBySubject(
                        subjectId
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NoteResponse>>
    getNotesByUser(
            @PathVariable Long userId) {

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
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest request) {

        return ResponseEntity.ok(
                noteService.updateNote(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable Long id) {

        noteService.deleteNote(id);

        return ResponseEntity.noContent().build();
    }
}