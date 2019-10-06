package ua.mycompany.domain;

public interface CustomerPrototype {
    CustomerPrototype clone(String newPassword);
}
