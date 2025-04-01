package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.model.InstructionalVideo;
import com.example.project.repository.InstructionalvideoRepo;

@Service
public class InstructionalvideoService {

    @Autowired
    private InstructionalvideoRepo instructionalVideoRepository;

    public InstructionalVideo createInstructionalVideo(InstructionalVideo video) {
        return instructionalVideoRepository.save(video);
    }

    public Optional<InstructionalVideo> getInstructionalVideoById(Long id) {
        return instructionalVideoRepository.findById(id);
    }

    public List<InstructionalVideo> getAllInstructionalVideos() {
        return instructionalVideoRepository.findAll();
    }

    public Optional<InstructionalVideo> updateInstructionalVideo(Long id, InstructionalVideo video) {
        return instructionalVideoRepository.findById(id).map(existingVideo -> {
            existingVideo.setTitle(video.getTitle());
            existingVideo.setUrl(video.getUrl());
            return instructionalVideoRepository.save(existingVideo);
        });
    }

    public boolean deleteInstructionalVideo(Long id) {
        if (instructionalVideoRepository.existsById(id)) {
            instructionalVideoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<InstructionalVideo> findVideosByTitle(String title) {
        return instructionalVideoRepository.findByTitle(title);
    }
}
