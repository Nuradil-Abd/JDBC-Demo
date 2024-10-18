package java15.services;

import java15.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    //create table
    void createCarTable ();

    //save
    String saveCar (Car newCar);
    //find by id
    Car getCarById(Long id);

    //find all
    List<Car> getAllCars ();

    //DELETE  by id
    String deleteCarByID (Long id);

    // update
    String updateCarByID (Long id, Car newCar);
}
