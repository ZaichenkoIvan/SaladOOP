package ua.mycompany.view;

import ua.mycompany.controller.CustomerController;
import ua.mycompany.domain.Customer;
import ua.mycompany.helper.sort.BubbleSort;
import ua.mycompany.helper.utility.UTF8Control;
import ua.mycompany.helper.validator.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class CustomerViewInfo {
    private CustomerController customerController;
    private Scanner in = new Scanner(System.in);
    private ResourceBundle lang;

    @Autowired
    public CustomerViewInfo(CustomerController customerController) {
        this.customerController = customerController;
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

    private void chooseLang(int chooseLanguage) {

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
        menu();
    }

    private void menu() {

        System.out.println(lang.getString("menu"));
        System.out.println("1 - " + lang.getString("viewCustomer"));
        System.out.println("2 - " + lang.getString("addCustomer"));
        System.out.println("3 - " + lang.getString("sortCustomer"));
        System.out.println("4 - " + lang.getString("loginCustomer"));
        System.out.println("5 - " + lang.getString("inputId"));
//        System.out.println("6 - " + lang.getString("inputIdDepartment"));
//        System.out.println("7 - " + lang.getString("inputGroup"));
//        System.out.println("8 - " + lang.getString("inputDepartmentAndCourse"));
        System.out.println("9 - " + lang.getString("chooseLanguage"));


        int choice;
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }

        switch (choice) {
            case 1:
                printAllCustomers(customerController.findAll());
                break;
            case 2:
                createCustomerFromConsole();
                break;
            case 3:
                sortCustomer();
                break;
            case 4:
                System.out.println(loginCustomer());
                break;
            case 5:
                System.out.println(findById());
                break;
//            case 6:
//                printAllCustomers(findByDepartment());
//                break;
//            case 7:
//                printAllCustomers(findByGroup());
//                break;
//            case 8:
//                printAllCustomers(findByDepartmentAndCourse());
//                break;
            case 9:
                chooseMenuLang();
                break;
        }
        menu();
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

    private void createCustomerFromConsole() {

        String name = writeFieldWithValidator("name");
        String surname = writeFieldWithValidator("surname");
        String email = writeFieldWithValidator("email");
        String phoneNumber = writeFieldWithValidator("phoneNumber");
        String birthday = writeFieldWithValidator("date");
//        int course = Integer.parseInt(writeFieldWithValidator("course"));

//        System.out.println(lang.getString("groupCustomer"));
//        String group = in.nextLine();

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

        customerController.register(customer);
        System.out.println(lang.getString("CustomerCreated") + "\n");

        menu();
    }


    private void sortCustomer() {
        System.out.println(lang.getString("CustomersAreSorted") + "\n");
        printAllCustomers(BubbleSort.sort(customerController.findAll()));
    }

    private Customer loginCustomer(){
        String email = writeFieldWithValidator("email");

        System.out.println(lang.getString("passwordCustomer"));
        String password = in.nextLine();

        return customerController.login(email,password);

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

    private Customer findById(){
        System.out.println(lang.getString("inputId"));
        return customerController.findById(in.nextLong());
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
