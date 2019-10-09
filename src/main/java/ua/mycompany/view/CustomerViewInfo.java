package ua.mycompany.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.controller.AdminController;
import ua.mycompany.controller.UserController;
import ua.mycompany.controller.VegetableController;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.customer.Role;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.util.localization.UTF8Control;
import ua.mycompany.util.sort.BubbleSort;
import ua.mycompany.util.validator.ValidatorFactory;

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
    private VegetableController vegetableController;
    private Scanner in = new Scanner(System.in);
    private ResourceBundle lang;
    private Customer currentCustomer;

    @Autowired
    public CustomerViewInfo(UserController userController, AdminController adminController, VegetableController vegetableController) {
        this.userController = userController;
        this.adminController = adminController;
        this.vegetableController = vegetableController;
    }

    public void run() {
        chooseMenuLang();
    }

    private void chooseMenuLang() {

        System.out.println("\n Choose language/Оберіть мову");
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
        System.out.println("4 - " + lang.getString("viewAllVegetables"));
        System.out.println("5 - " + lang.getString("deleteVegetables"));
        System.out.println("6 - " + lang.getString("viewOwnVegetables"));
        System.out.println("7 - " + lang.getString("addOwnVegetables"));
        System.out.println("8 - " + lang.getString("deleteOwnVegetables"));
        System.out.println("9 - " + lang.getString("sortOwnVegetables"));
        System.out.println("10 - " + lang.getString("rangeOwnVegetablesByCalories"));
        System.out.println("11 - " + lang.getString("sumOwnVegetablesByCalories"));
        System.out.println("12 - " + lang.getString("chooseLanguage"));
        System.out.println("13 - " + lang.getString("exit"));


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
            case 4:
                printAllVegetables(vegetableController.findAll());
                break;
            case 5:
                deleteVegetable();
                break;
            case 6:
                printAllVegetables(adminController.findAllVegetable(currentCustomer));
                break;
            case 7:
                addOwnVegetable();
                break;
            case 8:
                deleteOwnVegetable();
                break;
            case 9:
                printAllVegetables(adminController.sortSalad(currentCustomer));
                break;
            case 10:
                printAllVegetables(adminController.rangeByCalories(currentCustomer, 30, 70));
                break;
            case 11:
                System.out.println((adminController.summaryOfCaloriesSalad(currentCustomer)));
                break;
            case 12:
                chooseMenuLang();
                break;
            case 13:
                System.exit(0);
        }
        menuAdmin();

    }

    private void deleteOwnVegetable() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        adminController.deleteVegetable(currentCustomer, id);
    }

    private void addOwnVegetable() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        adminController.addVegetable(currentCustomer, id);
    }

    private void deleteVegetable() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        vegetableController.deleteById(id);
    }

    private void menuUser() {
        System.out.println(lang.getString("menu"));
        System.out.println("1 - " + lang.getString("currentId"));
        System.out.println("2 - " + lang.getString("viewAllVegetables"));
        System.out.println("3 - " + lang.getString("viewOwnVegetables"));
        System.out.println("4 - " + lang.getString("addOwnVegetables"));
        System.out.println("5 - " + lang.getString("deleteOwnVegetables"));
        System.out.println("6 - " + lang.getString("sortOwnVegetables"));
        System.out.println("7 - " + lang.getString("rangeOwnVegetablesByCalories"));
        System.out.println("8 - " + lang.getString("sumOwnVegetablesByCalories"));
        System.out.println("9 - " + lang.getString("chooseLanguage"));
        System.out.println("10 - " + lang.getString("exit"));

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
            case 2:
                printAllVegetables(vegetableController.findAll());
                break;
            case 3:
                printAllVegetables(userController.findAllVegetable(currentCustomer));
                break;
            case 4:
                addOwnVegetableUser();
                break;
            case 5:
                deleteOwnVegetableUser();
                break;
            case 6:
                printAllVegetables(userController.sortSalad(currentCustomer));
                break;
            case 7:
                printAllVegetables(userController.rangeByCalories(currentCustomer, 30, 70));
                break;
            case 8:
                System.out.println((userController.summaryOfCaloriesSalad(currentCustomer)));
                break;
            case 9:
                chooseMenuLang();
                break;
            case 10:
                System.exit(0);
        }
        menuUser();
    }

    private void deleteOwnVegetableUser() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        userController.deleteVegetable(currentCustomer, id);
    }

    private void addOwnVegetableUser() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        userController.addVegetable(currentCustomer, id);
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

    private void printAllVegetables(ArrayList<Vegetable> vegetables) {
        if (vegetables.isEmpty()) {
            System.out.println(lang.getString("noVegetablesYet"));
        } else {
            System.out.println("\n" + lang.getString("listVegetables"));
            for (Vegetable vegetable : vegetables
            ) {
                System.out.println(vegetable);
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

}
