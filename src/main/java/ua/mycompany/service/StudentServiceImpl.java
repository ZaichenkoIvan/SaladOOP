package ua.mycompany.service;


import ua.mycompany.domain.Student;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.UncorrectedLoginRuntimeException;
import ua.mycompany.exception.UserNotExistRuntimeException;
import ua.mycompany.helper.utility.PasswordUtils;
import ua.mycompany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student register(Student student) {
        if (student == null) {
            throw new UserNotExistRuntimeException("User not exist");
        }

        String encodePassword = PasswordUtils.generateSecurePassword(student.getPassword());

        Student studentWithEncodePassword = (Student) student.clone(encodePassword);

        return studentRepository.save(studentWithEncodePassword);
    }

    @Override
    public Student login(String email, String password) {
        String encodePassword = PasswordUtils.generateSecurePassword(password);

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new UncorrectedLoginRuntimeException("Login are not exist"));

        String studentPassword = student.getPassword();

        if (studentPassword.equals(encodePassword)) {
            return student;
        }
        throw new UncorrectedLoginRuntimeException("Password is uncorrected");
    }


    @Override
    public Student findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id of student must be positive");
        }

        Optional<Student> studentFindingById = studentRepository.findById(id);

        if (studentFindingById.isPresent()) {
            return studentFindingById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of user must be correct");
    }

    @Override
    public ArrayList<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void update(Student student) {
        if (student == null) {
            throw new UserNotExistRuntimeException("User not exist");
        }
        studentRepository.update(student);
    }

    @Override
    public Student deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id must be positive");
        }

        Optional<Student> studentFindingById = studentRepository.deleteById(id);

        if (studentFindingById.isPresent()) {
            return studentFindingById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of user must be correct");
    }
}

//    @Override
//    public ArrayList<Student> findByDepartment(Long idDepartment) {
//        if (idDepartment < 0) {
//            throw new UncorrectedIdRuntimeException("Id must be positive");
//        }
//
//        return studentRepository.findByDepartment(idDepartment);
//
//
//    }
//
//    @Override
//    public ArrayList<Student> findByYear(int year) {
//        if (year < 1900) {
//            throw new IllegalArgumentException("Year must be >1900");
//        }
//
//        return studentRepository.findByYear(year);
//    }
//
//    @Override
//    public ArrayList<Student> findByGroup(String group) {
//        if (group == null) {
//            throw new IllegalArgumentException("Group is not null");
//        }
//        return studentRepository.findByGroup(group);
//    }
//
//    @Override
//    public ArrayList<Student> findByDepartmentAndCourse(Long idDepartment, int course) {
//        if (course < 0 || course > 6 || idDepartment < 0) {
//            throw new IllegalArgumentException("Course must be in range [0;6] or id department must be possitive");
//        }
//        return studentRepository.findByDepartmentAndCourse(idDepartment, course);
//    }
//}
