package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.model.Yogaclass;
import com.example.project.repository.YogaclassRepo;

@Service
public class YogaclassService {
    @Autowired
    private YogaclassRepo yogaClassRepository;

    public Yogaclass createYogaClass(Yogaclass yogaClass) {
        return yogaClassRepository.save(yogaClass);
    }

    public Optional<Yogaclass> getYogaClassById(Long id) {
        return yogaClassRepository.findById(id);
    }

    public List<Yogaclass> getAllYogaClasses() {
        return yogaClassRepository.findAll();
    }

    public Optional<Yogaclass> updateYogaClass(Long id, Yogaclass updatedYogaClass) {
        return yogaClassRepository.findById(id).map(existingClass -> {
            existingClass.setName(updatedYogaClass.getName());
            existingClass.setDescription(updatedYogaClass.getDescription());
            return yogaClassRepository.save(existingClass);
        });
    }

    public boolean deleteYogaClass(Long id) {
        if (yogaClassRepository.existsById(id)) {
            yogaClassRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
