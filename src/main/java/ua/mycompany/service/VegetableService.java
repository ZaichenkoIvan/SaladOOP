package ua.mycompany.service;

import ua.mycompany.domain.order.Vegetable;

import java.util.ArrayList;

public interface VegetableService {
    Vegetable save(Vegetable customer);

    Vegetable findById(Long id);

    ArrayList<Vegetable> findAll();

    void update(Vegetable customer);

    Vegetable deleteById(Long id);
}
