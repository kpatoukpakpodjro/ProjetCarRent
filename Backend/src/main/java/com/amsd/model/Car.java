package com.amsd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plateNumber;
    private int price;
    private boolean rented;

    public Car() {}

    public Car(Long id, String plateNumber, int price, boolean rented) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.price = price;
        this.rented = rented;
    }

    public Long getId() { return id; }
    public String getPlateNumber() { return plateNumber; }
    public int getPrice() { return price; }
    public boolean isRented() { return rented; }

    public void setId(Long id) { this.id = id; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }
    public void setPrice(int price) { this.price = price; }
    public void setRented(boolean rented) { this.rented = rented; }
}