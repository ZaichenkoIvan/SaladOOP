package ua.mycompany.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class Student implements Comparable<Student>, StudentPrototype {
    private final Long id;
    private final String name;
    private final String surname;
    private final LocalDate birthday;
    private final Address address;
    private final String phoneNumber;
    private final String password;
    private static Long counter = 0L;

    private final String email;

    private final Comparator<Student> STUDENT_COMPARATOR_BY_AGE =
            Comparator.comparingInt(student -> LocalDate.now().getYear() - student.birthday.getYear());

    private final Comparator<Student> STUDENT_COMPARATOR_BY_NAME =
            Comparator.comparing(student -> student.name);

    private final Comparator<Student> STUDENT_COMPARATOR_BY_SURNAME =
            Comparator.comparing(student -> student.surname);

    public Comparator<Student> getStudentComparator() {
        return STUDENT_COMPARATOR_BY_NAME.thenComparing(STUDENT_COMPARATOR_BY_SURNAME.thenComparing(STUDENT_COMPARATOR_BY_AGE));
    }

    private Student(Builder builder) {
        if (builder.id == null) {
            this.id = ++counter;
        } else {
            this.id = builder.id;
        }
        this.name = builder.name;
        this.surname = builder.surname;
        this.birthday = builder.birthday;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.password = builder.password;
    }

    @Override
    public int compareTo(Student o) {
        return this.getStudentComparator().compare(this, o);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(birthday, student.birthday) &&
                Objects.equals(address, student.address) &&
                Objects.equals(phoneNumber, student.phoneNumber) &&
                Objects.equals(password, student.password) &&
                Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthday, address, phoneNumber, password, email);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public StudentPrototype clone(String newPassword) {
        Address address = (Address) Optional.ofNullable(this.address)
                .map(Address::clone)
                .orElse(null);

        return builder()
                .withId(id)
                .withName(name)
                .withSurname(surname)
                .withBirthday(birthday)
                .withAddress(address)
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withPassword(newPassword)
                .build();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String surname;
        private LocalDate birthday;
        private Address address;
        private String phoneNumber;
        private String email;
        private String password;

        private Builder() {
        }

        public Student build() {
            return new Student(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

    }
}
