package java15.dao;

import java15.config.DatabaseConnection;
import java15.models.Car;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {

    private final Connection connection;

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }





    @Override
    public void createCarTable() {
        String query = """
                                create table if not exists car (
                                id serial primary key,
                                name varchar unique not null,
                                color varchar not null,
                                engine varchar not null,
                                issueDate date not null
                )
                """;
        try(Statement statement = connection.createStatement()) {

            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveCar(Car newCar) {
        String query = """
                insert into car (name, color, engine, issueDate)
                values (?, ?, ?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, newCar.getModel());
            preparedStatement.setString(2, newCar.getColor());
            preparedStatement.setString(3, newCar.getEngine());
            preparedStatement.setDate(4, Date.valueOf(newCar.getIssueDate()));
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Car> getCarById(Long id) {

        String sql = "select * from car where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                return Optional.of(mapRowToCar(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Car> getAllCars() {
        String sql = "select * from car";
        List<Car> cars = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    cars.add(mapRowToCar(resultSet));
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public boolean deleteCarByID(Long id) {
        String sql = "delete from car where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error while deleting car",e);
        }

    }

    @Override
    public boolean updateCarByID(Long id, Car newCar) {
        String sql = "update car set name = ?, color = ?, engine = ?, issueDate = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, newCar.getModel());
            preparedStatement.setString(2, newCar.getColor());
            preparedStatement.setString(3, newCar.getEngine());
            preparedStatement.setDate(4, Date.valueOf(newCar.getIssueDate()));
            preparedStatement.setLong(5, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error while updating car",e);
        }
    }

    private Car mapRowToCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong("id"));
        car.setModel(resultSet.getString("name"));
        car.setColor(resultSet.getString("color"));
        car.setEngine(resultSet.getString("engine"));
        car.setIssueDate(resultSet.getDate("issueDate").toLocalDate());
        return car;
    }
    @Override
    public boolean carExists(Long id) {
        String sql = "select * from car where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setLong(1, id);
           ResultSet resultSet = preparedStatement.executeQuery();
           if (resultSet.next()) {
               return resultSet.getInt(1) > 0;
           }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while checking if car exists",e);
        }
        return false;
    }
}
