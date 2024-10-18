package java15;

import java15.config.DatabaseConnection;
import java15.models.Car;
import java15.services.CarService;
import java15.services.CarServiceImpl;

import java.sql.Connection;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        CarService carService = new CarServiceImpl();;
        carService.createCarTable();
//        String resultMessage = carService.saveCar(new Car("BMW",
//                "black",
//                "2,5",
//                LocalDate.of(2020, 10, 20)));
//        System.out.println(resultMessage);
        try {
            System.out.println(carService.getCarById(6L));
        } catch (Exception e) {
            {
                System.out.println(e.getMessage());
            }
        }
        //get all
        System.out.println(carService.getAllCars());
        //deleted by id
        System.out.println(carService.deleteCarByID(2L));
        System.out.println(carService.getAllCars());
        //update car by id

        System.out.println(carService.updateCarByID(3L, new Car("Chevrolet", "Yellow", "2,0", LocalDate.of(2019, 3, 23))));
        System.out.println(carService.getAllCars());
    }
}
