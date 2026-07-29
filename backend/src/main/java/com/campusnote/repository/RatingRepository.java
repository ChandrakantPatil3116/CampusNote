package com.campusnote.repository;

import com.campusnote.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    Optional<Rating> findByUserUserIdAndNoteNoteId(Integer userId, Integer noteId);
}