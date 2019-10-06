package ua.mycompany.init;

import ua.mycompany.domain.Address;
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
        Student ivan = Student.builder()
                .withName("Ivan")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380911111111")
                .withEmail("ivan@gmail.com")
                .withPassword("ivanIVAN70")
                .build();

        Student vasyl = Student.builder()
                .withName("Vasyl")
                .withSurname("Zaichenko")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 14))
                .withPhoneNumber("380922222222")
                .withEmail("vasv@gmail.com")
                .withPassword("vasVas25")
                .build();

        Student volodymyr = Student.builder()
                .withName("Volodymyr")
                .withSurname("Tsaruk")
                .withBirthday(LocalDate.of(1999, 6, 11))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380933333333")
                .withEmail("vv@gmail.com")
                .withPassword("vovaVOVA7")
                .build();

        studentService.register(ivan);
        studentService.register(vasyl);
        studentService.register(volodymyr);

    }
}
