package java15.dao;

import java15.models.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {
    //create table
    void createCarTable ();

    //save
    boolean saveCar (Car newCar);
    //find by id

    Optional<Car> getCarById(Long id);

    //find all
    List<Car> getAllCars ();

    //DELETE  by id
    boolean deleteCarByID (Long id);

    // update
    boolean updateCarByID (Long id, Car newCar);

    boolean carExists(Long id);
}
