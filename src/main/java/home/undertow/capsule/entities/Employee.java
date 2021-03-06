package home.undertow.capsule.entities;

import lombok.Data;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by alex on 9/5/2015.
 */
@Data
@Entity
@Cacheable(true)
public class Employee {

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String description;

    private Employee() {}

    public Employee(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }
}