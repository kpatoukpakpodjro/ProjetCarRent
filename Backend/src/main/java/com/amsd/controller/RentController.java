package com.amsd.controller;

import com.amsd.model.Car;
import com.amsd.model.Rent;
import com.amsd.model.User;
import com.amsd.repository.CarRepository;
import com.amsd.repository.RentRepository;
import com.amsd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rents")
class RentController {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/rent")
    public Rent rentCar(@RequestParam Long carId, @RequestParam Long userId ) {
        Optional<Car> carOpt = carRepository.findById(carId);
        Optional<User> user = userRepository.findById(userId);
        if (carOpt.isPresent() && !carOpt.get().isRented()) {
            Car car = carOpt.get();
            car.setRented(true);
            carRepository.save(car);

            LocalDate beginDate = LocalDate.now();
            Rent rent = new Rent(null, car, user.get(), beginDate, null);
            return rentRepository.save(rent);
        }
        throw new RuntimeException("Car not available for rent");
    }

    @PostMapping("/return")
    public void returnCar(@RequestParam Long carId) {
        Optional<Car> carOpt = carRepository.findById(carId);

        if (carOpt.isPresent() && carOpt.get().isRented()) {
            Car car = carOpt.get();
            car.setRented(false);
            carRepository.save(car);
            Optional<Rent> rentOpt = rentRepository.findActiveRentByCarId(carId);
            if (rentOpt.isPresent()) {
                Rent rent = rentOpt.get();
                rent.setEndDate(LocalDate.now());
                rentRepository.save(rent);
            }
        } else {
            throw new RuntimeException("Car is not rented");
        }
    }
    @GetMapping("/myrents")
    public List<Car> getMyRentedCars(@RequestParam Long userId) {
        // Récupérer l'utilisateur
        Optional<User> user = userRepository.findById(userId);

        // Récupérer les locations de cet utilisateur
        List<Rent> userRents = rentRepository.findByUserId(user.get().getId());

        // Extraire les voitures louées
        return userRents.stream().map(Rent::getCar).collect(Collectors.toList());
    }

    @GetMapping
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }
}

