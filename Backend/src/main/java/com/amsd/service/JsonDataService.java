package com.amsd.service;

import com.amsd.model.Car;
import com.amsd.model.HasId;
import com.amsd.model.Rent;
import com.amsd.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JsonDataService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String usersFilePath = "src/main/resources/data/users.json";
    private final String carsFilePath = "src/main/resources/data/cars.json";
    private final String rentsFilePath = "src/main/resources/data/rents.json";

    public JsonDataService() {
        objectMapper.registerModule(new JavaTimeModule()); // Enregistrement du module
        initializeCounters();
    }
    // 🟢 Charger les ID existants et initialiser les compteurs
    private void initializeCounters() {
        try {
            Car.setCarCounter(getMaxId(getAllCars()));
            User.setUserCounter(getMaxId(getAllUsers()));
            Rent.setRentCounter(getMaxId(getAllRents()));
        } catch (IOException e) {
            System.err.println("Erreur lors de l'initialisation des compteurs : " + e.getMessage());
        }
    }

    private <T extends HasId> long getMaxId(List<T> items) {
        return items.stream().mapToLong(HasId::getId).max().orElse(0);
    }

    // Méthodes pour User
    public List<User> getAllUsers() throws IOException {
        return objectMapper.readValue(new File(usersFilePath), new TypeReference<List<User>>() {});
    }

    public Optional<User> getUserById(Long id) throws IOException {
        List<User> users = getAllUsers();
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public void saveUser(User user) throws IOException {
        List<User> users = getAllUsers();
        users.add(user);
        objectMapper.writeValue(new File(usersFilePath), users);
    }

    // Méthodes pour Car
    public List<Car> getAllCars() throws IOException {
        return objectMapper.readValue(new File(carsFilePath), new TypeReference<List<Car>>() {});
    }

    public void saveCar(Car car) throws IOException {
        List<Car> cars = getAllCars();
        //cars.add(car);
        // Cherche la voiture par ID et met à jour son statut 'rented'
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getId().equals(car.getId())) {  // Compare par ID
                cars.get(i).setRented(car.isRented());  // Met à jour le statut 'rented'
                break;
            }
        }
        objectMapper.writeValue(new File(carsFilePath), cars);
    }

    // Méthodes pour Rent
    public List<Rent> getAllRents() throws IOException {
        return objectMapper.readValue(new File(rentsFilePath), new TypeReference<List<Rent>>() {});
    }

    public void saveRent(Rent rent) throws IOException {
        List<Rent> rents = getAllRents();
        Rent rentToAdd = rent;
        // Vérifie si la voiture est déjà louée
        Optional<Rent> existingRent = rents.stream()
                .filter(r -> r.getCar().getId().equals(rent.getCar().getId()) && r.getEndDate() == null)
                .findFirst();

        if (existingRent.isPresent()) {
            // Si la voiture est déjà louée, on met à jour l'endDate
            Rent rentToUpdate = existingRent.get();
            rentToUpdate.setEndDate(rent.getEndDate()); // Met à jour la date de fin
            rentToUpdate.setInvoice(rent.getInvoice()); // Met à jour la facture (si nécessaire)
            rentToAdd = rentToUpdate;
        }
        rents.add(rentToAdd);

        // Sauvegarde les changements dans le fichier JSON
        objectMapper.writeValue(new File(rentsFilePath), rents);
    }
}