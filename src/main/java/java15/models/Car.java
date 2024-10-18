package java15.models;

import lombok.*;

import java.time.LocalDate;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Car {
    private Long id;
    private String model;
    private String color;
    private String engine;
    private LocalDate issueDate;

    public Car( String model, String color, String engine, LocalDate issueDate) {

        this.model = model;
        this.color = color;
        this.engine = engine;
        this.issueDate = issueDate;
    }
}
