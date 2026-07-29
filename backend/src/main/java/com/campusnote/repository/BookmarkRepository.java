package com.campusnote.repository;

import com.campusnote.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    List<Bookmark> findByUserUserId(Integer userId);
}