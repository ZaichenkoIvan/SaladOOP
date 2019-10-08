package ua.mycompany.domain.customer;

public interface CustomerPrototype {
    CustomerPrototype clone(String newPassword);
}
