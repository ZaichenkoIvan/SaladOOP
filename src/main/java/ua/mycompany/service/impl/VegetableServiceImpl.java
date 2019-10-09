package ua.mycompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mycompany.domain.order.Vegetable;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.VegetableNotExistRuntimeException;
import ua.mycompany.repository.VegetableRepository;
import ua.mycompany.service.VegetableService;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VegetableServiceImpl implements VegetableService {
    private VegetableRepository vegetableRepository;

    @Autowired
    public VegetableServiceImpl(VegetableRepository vegetableRepository) {
        this.vegetableRepository = vegetableRepository;
    }

    @Override

    public Vegetable save(Vegetable vegetable) {
        if (vegetable == null) {
            throw new VegetableNotExistRuntimeException("Vegetable is not exist");
        }

        return vegetableRepository.save(vegetable);
    }

    @Override
    public Vegetable findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id must be >0");
        }

        Optional<Vegetable> vegetableFindingById = vegetableRepository.findById(id);

        return vegetableFindingById.orElseThrow(() -> new UncorrectedIdRuntimeException("Id of Vegetable must be correct"));

    }

    @Override
    public ArrayList<Vegetable> findAll() {
        return vegetableRepository.findAll();
    }

    @Override
    public void update(Vegetable vegetable) {
        if (vegetable == null) {
            throw new VegetableNotExistRuntimeException("Vegetable not exist");
        }
        vegetableRepository.update(vegetable);
    }

    @Override
    public Vegetable deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id of vegetable must be > 0");
        }
        Optional<Vegetable> vegetableDeleteById = vegetableRepository.deleteById(id);
        if (vegetableDeleteById.isPresent()) {
            return vegetableDeleteById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of vegetable uncorrected");
    }
}
