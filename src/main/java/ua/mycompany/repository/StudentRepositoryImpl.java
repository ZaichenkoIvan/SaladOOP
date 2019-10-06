package ua.mycompany.repository;

import ua.mycompany.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private Map<Long, Student> idToStudents = new HashMap<>();
    private static Long counter = 0L;

    @Autowired
    public StudentRepositoryImpl() {
    }

    @Override
    public Student save(Student student) {
        return idToStudents.put(++counter, student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(idToStudents.get(id));
    }

    @Override
    public ArrayList<Student> findAll() {
        return new ArrayList<>(idToStudents.values());
    }

    @Override
    public void update(Student student) {
        idToStudents.replace(student.getId(), student);
    }

    @Override
    public Optional<Student> deleteById(Long id) {
        return Optional.ofNullable(idToStudents.remove(id));
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        Student student = null;
        for (Long i = 1L; i < idToStudents.size() + 1; i++) {
            if (idToStudents.get(i).getEmail().equals(email)) {
                student = idToStudents.get(i);
                break;
            }
        }
        return Optional.ofNullable(student);
    }
//
//    public ArrayList<Student> findByDepartment(Long idDepartment) {
//        ArrayList<Student> findByFacultyStudents = new ArrayList<>();
//        for (Long i = 1L; i < idToStudents.size() + 1; i++) {
//            if (idDepartment.equals(idToStudents.get(i).getDepartment().getId())) {
//                findByFacultyStudents.add(idToStudents.get(i));
//            }
//        }
//        return findByFacultyStudents;
//    }
//
//    public ArrayList<Student> findByYear(int year) {
//        ArrayList<Student> findByYearStudents = new ArrayList<>();
//        for (Long i = 1L; i < idToStudents.size() + 1; i++) {
//            if (year < idToStudents.get(i).getBirthday().getYear()) {
//                findByYearStudents.add(idToStudents.get(i));
//            }
//        }
//        return findByYearStudents;
//    }
//
//    @Override
//    public ArrayList<Student> findByGroup(String group) {
//        ArrayList<Student> findByGroupStudents = new ArrayList<>();
//        for (Long i = 1L; i < idToStudents.size() + 1; i++) {
//            if (group.equals(idToStudents.get(i).getGroup())) {
//                findByGroupStudents.add(idToStudents.get(i));
//            }
//        }
//        return findByGroupStudents;
//    }
//
//    public ArrayList<Student> findByDepartmentAndCourse(Long idDepartment, int cource) {
//        ArrayList<Student> findByDepartmentAndCourseStudents = new ArrayList<>();
//        for (Long i = 1L; i < idToStudents.size() + 1; i++) {
//            if (idDepartment.equals(idToStudents.get(i).getDepartment().getId())
//                    && cource == idToStudents.get(i).getCourse()) {
//                findByDepartmentAndCourseStudents.add(idToStudents.get(i));
//            }
//        }
//        return findByDepartmentAndCourseStudents;
//    }
}
