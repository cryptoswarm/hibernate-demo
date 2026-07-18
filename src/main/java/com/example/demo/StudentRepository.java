package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s from Student s where s.firstName = ?1 And s.age >= ?2")
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqual(String firstName, Integer age);

    @Query(
            value = "SELECT * from Student where first_name = :firstName and age >= :age",
            nativeQuery = true
    )
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(@Param("firstName") String firstName, @Param("age") Integer age);

    @Transactional
    @Modifying
    @Query("delete from Student u where u.id = ?1")
    int deleteStudentById(Long id);
}
