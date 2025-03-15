package com.amsd.model;

import jakarta.persistence.*;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

@Entity
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate beginDate;
    private LocalDate endDate;
    private float invoice;
    public Rent() {}

    public Rent(Long id, Car car, User user, LocalDate beginDate, LocalDate endDate) {
        this.id = id;
        this.car = car;
        this.user = user;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.invoice = 0;
    }
    // Getters
    public Long getId() { return id; }
    public Car getCar() { return car; }
    public User getUser() { return user; }
    public LocalDate getBeginDate() { return beginDate; }
    public LocalDate getEndDate() { return endDate; }
    public float getInvoice() {return invoice;}

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setCar(Car car) { this.car = car; }
    public void setUser(User user) { this.user = user; }
    public void setBeginDate(LocalDate beginDate) { this.beginDate = beginDate; }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        // Calcul du montant total de la location
        Long daysBetween = ChronoUnit.DAYS.between(this.endDate, this.beginDate); // Durée en jour de la location
            daysBetween = daysBetween +1;
        this.invoice = daysBetween * this.car.getPrice();
        }
    public void setInvoice( float invoice){ this.invoice = invoice;}
}