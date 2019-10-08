package ua.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.service.VegetableService;

import java.util.ArrayList;

@Component
public class VegetableController {

    private VegetableService vegetableService;

    @Autowired
    public VegetableController(VegetableService vegetableService) {
        this.vegetableService = vegetableService;
    }

    public Vegetable save(Vegetable vegetable) {
        return vegetableService.save(vegetable);
    }

    public Vegetable findById(Long id) {
        return vegetableService.findById(id);
    }

    public ArrayList<Vegetable> findAll() {
        return vegetableService.findAll();
    }

    public void update(Vegetable vegetable) {
        vegetableService.update(vegetable);
    }

    public Vegetable deleteById(Long id) {
        return vegetableService.deleteById(id);
    }
}
