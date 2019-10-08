package ua.mycompany.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.controller.AdminController;
import ua.mycompany.controller.UserController;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.customer.Role;
import ua.mycompany.helper.sort.BubbleSort;
import ua.mycompany.helper.utility.UTF8Control;
import ua.mycompany.helper.validator.ValidatorFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

@Component
public class CustomerViewInfo {
    private UserController userController;
    private AdminController adminController;
    private Scanner in = new Scanner(System.in);
    private ResourceBundle lang;
    private Customer currentCustomer;

    @Autowired
    public CustomerViewInfo(UserController userController, AdminController adminController) {
        this.userController = userController;
        this.adminController = adminController;
    }

    public void run() {
        chooseMenuLang();
    }

    private void chooseMenuLang() {

        System.out.println("Choose language/Оберіть мову");
        System.out.println("English (1)");
        System.out.println("Українська (2)");
        int chooseLanguage = in.nextInt();

        chooseLang(chooseLanguage);
    }

    public void chooseLang(int chooseLanguage) {

        try {
            if (chooseLanguage == 1) {
                lang = ResourceBundle.getBundle("resources", new Locale("en"), new UTF8Control());
            } else if (chooseLanguage == 2) {
                lang = ResourceBundle.getBundle("resources", new Locale("ua"), new UTF8Control());
            } else
                chooseMenuLang();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }
        loginOrRegister();
    }

    private void loginOrRegister() {
        System.out.println("1 - " + lang.getString("registration"));
        System.out.println("2 - " + lang.getString("login"));
        int loginOrRegister = in.nextInt();
        if (loginOrRegister == 1) {
            register();
        } else if (loginOrRegister == 2) {
            loginCustomer();
        } else {
            loginOrRegister();
        }

    }

    private void menu() {
        if (currentCustomer.getRole() == Role.ADMIN) {
            menuAdmin();
        } else {
            menuUser();
        }
    }

    private void menuAdmin() {
        System.out.println(lang.getString("menu"));
        System.out.println("1 - " + lang.getString("viewCustomer"));
        System.out.println("2 - " + lang.getString("sortCustomer"));
        System.out.println("3 - " + lang.getString("inputId"));

        System.out.println("9 - " + lang.getString("chooseLanguage"));
        System.out.println("0 - " + lang.getString("exit"));


        int choice;
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }

        switch (choice) {
            case 1:
                printAllCustomers(adminController.findAll());
                break;
            case 2:
                sortCustomer();
                break;
            case 3:
                System.out.println(findById());
                break;
            case 9:
                chooseMenuLang();
                break;
            case 0:
                System.exit(0);
        }
        menuAdmin();

    }

    private void menuUser() {
        System.out.println(lang.getString("menu"));
        System.out.println("1 - " + lang.getString("viewInfoUser"));

        System.out.println("8 - " + lang.getString("chooseLanguage"));
        System.out.println("9 - " + lang.getString("exit"));


        int choice;
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }

        switch (choice) {
            case 1:
                System.out.println(userController.findById(currentCustomer.getId()));
                break;
            case 8:
                chooseMenuLang();
                break;
            case 9:
                System.exit(0);
        }
        menuUser();
    }


    private void register() {

        String name = writeFieldWithValidator("name");
        String surname = writeFieldWithValidator("surname");
        String email = writeFieldWithValidator("email");
        String phoneNumber = writeFieldWithValidator("phoneNumber");
        String birthday = writeFieldWithValidator("date");
        System.out.println(lang.getString("passwordCustomer"));
        String password = in.nextLine();

        Customer customer = Customer.builder()
                .withName(name)
                .withSurname(surname)
                .withBirthday(parseStringToLocalDate(birthday))
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withPassword(password)
                .build();

        userController.register(customer);
        System.out.println(lang.getString("CustomerCreated") + "\n");
        currentCustomer = customer;
        menu();
    }

    private void loginCustomer() {
        String email = writeFieldWithValidator("email");

        System.out.println(lang.getString("passwordCustomer"));
        String password = in.nextLine();

        currentCustomer = userController.login(email, password);
        menu();
    }

    private LocalDate parseStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return LocalDate.parse(date, formatter);

    }

    private String writeFieldWithValidator(String nameField) {

        String key = nameField + "Customer";
        System.out.println(lang.getString(key));
        String fieldInput = in.nextLine();

        if (!(ValidatorFactory.getValidator(nameField).validate(fieldInput))) {
            System.out.println(lang.getString("uncorrectedArgument"));
            fieldInput = writeFieldWithValidator(nameField);
        }

        return fieldInput;
    }

    private void printAllCustomers(ArrayList<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println(lang.getString("noCustomerYet"));
        } else {
            System.out.println("\n" + lang.getString("listCustomer"));
            for (Customer customer : customers
            ) {
                System.out.println(customer);
            }
            System.out.println();
        }
    }

    private void sortCustomer() {
        System.out.println(lang.getString("CustomersAreSorted") + "\n");
        printAllCustomers(BubbleSort.sort(adminController.findAll()));
    }

    private Customer findById() {
        System.out.println(lang.getString("inputId"));
        return adminController.findById(in.nextLong());
    }

//
//    private ArrayList<Customer> findByDepartment(){
//        System.out.println(lang.getString("inputIdDepartment"));
//        return CustomerController.findByDepartment(in.nextLong());
//    }
//
//    private ArrayList<Customer> findByGroup(){
//        System.out.println(lang.getString("inputGroup"));
//        String group = in.nextLine();
//        group = in.nextLine();
//        return CustomerController.findByGroup(group);
//    }
//
//    private ArrayList<Customer> findByDepartmentAndCourse(){
//        System.out.println(lang.getString("inputIdDepartment"));
//        Long department = in.nextLong();
//        System.out.println(lang.getString("inputCourse"));
//        int course = in.nextInt();
//        return CustomerController.findByDepartmentAndCourse(department,course);
//    }

}
