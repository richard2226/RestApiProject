package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Yogaclass;
import com.example.project.service.YogaclassService;

@RestController
@RequestMapping("/api/classes")
public class YogaclassController {

    @Autowired
    private YogaclassService yogaClassService;

    @PostMapping
    public ResponseEntity<Yogaclass> createYogaClass(@RequestBody Yogaclass yogaClass) {
        Yogaclass createdClass = yogaClassService.createYogaClass(yogaClass);
        return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Yogaclass> getYogaClassById(@PathVariable Long id) {
        Optional<Yogaclass> yogaClass = yogaClassService.getYogaClassById(id);
        return yogaClass.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Yogaclass>> getAllYogaClasses() {
        List<Yogaclass> yogaClasses = yogaClassService.getAllYogaClasses();
        return new ResponseEntity<>(yogaClasses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Yogaclass> updateYogaClass(@PathVariable Long id, @RequestBody Yogaclass yogaClass) {
        Optional<Yogaclass> updatedClass = yogaClassService.updateYogaClass(id, yogaClass);
        return updatedClass.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteYogaClass(@PathVariable Long id) {
        boolean isDeleted = yogaClassService.deleteYogaClass(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}