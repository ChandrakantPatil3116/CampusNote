package com.campusnote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NoteRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "File path is required")
    private String filePath;

    @NotNull(message = "Subject ID is required")
    private Integer subjectId;

    @NotNull(message = "Uploaded by user ID is required")
    private Integer uploadedById;

    // Default constructor
    public NoteRequest() {
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getUploadedById() {
        return uploadedById;
    }

    public void setUploadedById(Integer uploadedById) {
        this.uploadedById = uploadedById;
    }
}