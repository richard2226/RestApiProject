package com.example.project.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.project.model.InstructionalVideo;
import com.example.project.service.InstructionalvideoService;

@RestController
@RequestMapping("/api/videos")
public class InstructionalVideoController {

    private final InstructionalvideoService instructionalVideoService;

    public InstructionalVideoController(InstructionalvideoService instructionalVideoService) {
        this.instructionalVideoService = instructionalVideoService;
    }

    @PostMapping
    public ResponseEntity<InstructionalVideo> createInstructionalVideo(@RequestBody InstructionalVideo video) {
        InstructionalVideo createdVideo = instructionalVideoService.createInstructionalVideo(video);
        return new ResponseEntity<>(createdVideo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructionalVideo> getInstructionalVideoById(@PathVariable Long id) {
        Optional<InstructionalVideo> video = instructionalVideoService.getInstructionalVideoById(id);
        return video.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<InstructionalVideo>> getAllInstructionalVideos() {
        List<InstructionalVideo> videos = instructionalVideoService.getAllInstructionalVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<InstructionalVideo>> findVideosByTitle(@RequestParam String title) {
        List<InstructionalVideo> videos = instructionalVideoService.findVideosByTitle(title);
        return videos.isEmpty() 
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND) 
            : new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructionalVideo> updateInstructionalVideo(@PathVariable Long id, @RequestBody InstructionalVideo video) {
        Optional<InstructionalVideo> updatedVideo = instructionalVideoService.updateInstructionalVideo(id, video);
        return updatedVideo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructionalVideo(@PathVariable Long id) {
        boolean isDeleted = instructionalVideoService.deleteInstructionalVideo(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
