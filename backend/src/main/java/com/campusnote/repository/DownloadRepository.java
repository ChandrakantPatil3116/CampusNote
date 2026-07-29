package com.campusnote.repository;

import com.campusnote.entity.Download;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownloadRepository extends JpaRepository<Download, Integer> {

}