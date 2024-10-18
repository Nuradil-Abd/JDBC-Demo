package java15.services;

import java15.config.DatabaseConnection;
import java15.dao.CarDao;
import java15.dao.CarDaoImpl;
import java15.models.Car;
import java.util.List;
import java.util.NoSuchElementException;


public class CarServiceImpl implements CarService {

    private final CarDao carDao;
    public CarServiceImpl() {
        this.carDao = new CarDaoImpl(DatabaseConnection.getConnection());
    }


    @Override
    public void createCarTable() {
        carDao.createCarTable();
    }

    @Override
    public String saveCar(Car newCar) {
        boolean saveCar = carDao.saveCar(newCar);
        return saveCar ? "Car Saved" : "Car Not Saved";
    }

    @Override
    public Car getCarById(Long id) {

        return carDao.getCarById(id).orElseThrow(() ->
                new NoSuchElementException("Car with id " + id + " not found"));
    }

    @Override
    public List<Car> getAllCars() {
        List <Car> cars = carDao.getAllCars();
        if (cars.isEmpty()) {
            throw new NoSuchElementException("No cars found");
        }
        return cars;
    }

    @Override
    public String deleteCarByID(Long id) {

        if (!carDao.carExists(id)) {
            return "Car with id " + id + " not found";
        }
        boolean deleted = carDao.deleteCarByID(id);
        return deleted ? "Car  with id " + id + " deleted successfully" : "Failed to delete car with id " + id;
    }

    @Override
    public String updateCarByID(Long id, Car newCar) {
        if(!carDao.carExists(id)) {
            throw new NoSuchElementException("Car with id " + id + " not found");
        }
        boolean update = carDao.updateCarByID(id, newCar);
        return update ? "Car Updated" : "Car Not Updated";
    }
}
