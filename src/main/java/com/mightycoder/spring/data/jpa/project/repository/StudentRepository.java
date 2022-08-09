package com.mightycoder.spring.data.jpa.project.repository;

import com.mightycoder.spring.data.jpa.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findByFirstName(String firstName);

    List<Student> findByLastNameContainingIgnoreCase(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String name);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select s.firstName,s.lastName from Student s where s.emailId = ?1")
    List<String> getStudentFirstNameByEmailAddress(String emailAddress);

    @Query(value = "select s.* from tbl_students s where s.email_address = ?1", nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailAddress);


    @Query(
            value = "select s.* from tbl_students s where s.email_address = :emailId",
            nativeQuery = true)
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailAddress);

//        @Procedure(value = "UPDATE_STUDENT")
    @Query(value = "call UPDATE_STUDENT(?,?,?,null)", nativeQuery = true)
    Integer updateStudent( String emailAddress,
                           String firstName,
                           String lastName);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_students set first_name = ? where email_address = ?",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);

}
