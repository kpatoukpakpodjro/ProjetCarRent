package com.amsd.controller;

import com.amsd.model.Car;

import com.amsd.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cars")
class CarController {
    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping("/available")
    public List<Car> getAvailableCars(@RequestParam(required = false) String sort) {
        List<Car> cars;
        if ("price".equalsIgnoreCase(sort)) {
            return carRepository.findAvailableCarsSortedByPrice();
        }
        return carRepository.findByRentedFalse();
    }
}
