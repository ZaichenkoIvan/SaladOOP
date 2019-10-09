package ua.mycompany.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.repository.VegetableRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class VegetableRepositoryImpl implements VegetableRepository {
    private Map<Long, Vegetable> idToVegetable = new HashMap<>();
    private static Long counter = 0L;

    @Autowired
    public VegetableRepositoryImpl() {
    }

    @Override
    public Vegetable save(Vegetable vegetable) {
        return idToVegetable.put(++counter, vegetable);
    }

    @Override
    public Optional<Vegetable> findById(Long id) {
        return Optional.ofNullable(idToVegetable.get(id));
    }

    @Override
    public ArrayList<Vegetable> findAll() {
        return new ArrayList<>(idToVegetable.values());
    }

    @Override
    public void update(Vegetable vegetable) {
        idToVegetable.replace(vegetable.getId(), vegetable);
    }

    @Override
    public Optional<Vegetable> deleteById(Long id) {
        return Optional.ofNullable(idToVegetable.remove(id));
    }
}
