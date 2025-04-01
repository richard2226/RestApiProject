package com.example.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    
    @Modifying
    @Query(value="insert into User(id,username,password,email,firstName,lastName,phoneNumber,address) values (:id,:username,:password,:email,:firstName,:lastName,:phoneNumber,:address) from User",nativeQuery=true)
    User registerUser(@Param("id") Long id,@Param("username") String username,@Param("password") String password,@Param("email") String email,@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("phoneNumber") String phoneNumber,@Param("address") String address);
    
    @Query(value="select * from User",nativeQuery=true)
    List<User>getAllUsers();

    @Query(value="select * from User where id=:id",nativeQuery=true)
    Optional<User>getUserById(@Param("id") Long id);
     
    @Query(value="select * from User",nativeQuery=true)
    Page<User> getUsersWithPagination(Pageable pageable);

}
