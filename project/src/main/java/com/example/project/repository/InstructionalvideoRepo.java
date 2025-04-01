package com.example.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.InstructionalVideo;

@Repository
public interface InstructionalvideoRepo extends JpaRepository<InstructionalVideo, Long> {
    
    @Query("SELECT iv FROM InstructionalVideo iv WHERE iv.id = :id")
    Optional<InstructionalVideo> getInstructionalVideoById(@Param("id") Long id);

    @Query("SELECT iv FROM InstructionalVideo iv WHERE iv.title LIKE %:title%")
    List<InstructionalVideo> findByTitle(@Param("title") String title);
    
    @Query(value = "select * from InstructionalVideo", nativeQuery = true)
    List<InstructionalVideo> getAllInstructionalVideos();
}
