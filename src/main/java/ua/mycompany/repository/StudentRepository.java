package ua.mycompany.repository;


import ua.mycompany.domain.Student;

import java.util.ArrayList;
import java.util.Optional;


public interface StudentRepository {
    Optional<Student> save(Student student);

    Optional<Student> findById(Long id);

    ArrayList<Student> findAll();

    void update(Student student);

    Optional<Student> deleteById(Long id);

    Optional<Student> findByEmail(String email);

    ArrayList<Student> findByDepartment(Long idDepartment);

    ArrayList<Student> findByYear(int year);

    ArrayList<Student> findByGroup(String group);

    ArrayList<Student> findByDepartmentAndCourse(Long idDepartment, int course);
}
