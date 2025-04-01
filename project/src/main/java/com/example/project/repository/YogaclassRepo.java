package com.example.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.Yogaclass;

@Repository
public interface YogaclassRepo extends JpaRepository<Yogaclass,Long> {
    
    Optional<Yogaclass> findByName(String name);

    @Query("SELECT yc FROM Yogaclass yc WHERE yc.id = :id")
    Optional<Yogaclass> getYogaClassById(@Param("id")Long id);
    @Modifying
    @Query(value="insert into Yogaclass(id,name,description) values (:id,:name,:description) from YogaClass",nativeQuery=true)
    Yogaclass createYogaClass(@Param("id") Long id,@Param("name") String name,@Param("description") String description);
    
    @Query(value="select * from Yogaclass",nativeQuery=true)
    List<Yogaclass>getAllYogaClasses();
}
