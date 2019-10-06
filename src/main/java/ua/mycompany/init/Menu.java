package ua.mycompany.init;

import ua.mycompany.domain.Address;
import ua.mycompany.domain.Department;
import ua.mycompany.domain.Student;
import ua.mycompany.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Menu {

    private StudentService studentService;

    @Autowired
    public Menu(StudentService studentService) {
        this.studentService = studentService;
    }

    public void run() {

        Department kpi = new Department(228L, "KPI");

        Student ivan = Student.builder()
                .withName("Ivan")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 13))
                .withGroup("IP-62")
                .withPhoneNumber("380911111111")
                .withCourse(5)
                .withDepartment(kpi)
                .withEmail("ivan@gmail.com")
                .withPassword("ivanIVAN70")
                .build();

        Student vasyl = Student.builder()
                .withName("Vasyl")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 14))
                .withGroup("IP-64")
                .withPhoneNumber("380922222222")
                .withCourse(4)
                .withDepartment(kpi)
                .withEmail("vasv@gmail.com")
                .withPassword("vasVas25")
                .build();

        Student volodymyr = Student.builder()
                .withName("Volodymyr")
                .withSurname("Tsaruk")
                .withBirthday(LocalDate.of(1999, 6, 11))
                .withAddress(new Address("Uman", "South", 13))
                .withGroup("IP-64")
                .withPhoneNumber("380933333333")
                .withCourse(4)
                .withDepartment(kpi)
                .withEmail("vv@gmail.com")
                .withPassword("vovaVOVA7")
                .build();

        studentService.register(ivan);
        studentService.register(vasyl);
        studentService.register(volodymyr);

    }
}
