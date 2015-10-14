package home.undertow.capsule.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.bson.Document;

/**
 * Created by alex on 9/26/2015.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Person {
    @JsonIgnore
    private String _id;
    private int age;
    private String gender;
    private String name;
    public Person(){}
    public Person(int age, String gender, String name) {
        this.age = age;
        this.gender = gender;
        this.name = name;
    }

    public static Person convert(Document document){
        return new Person(
                document.getDouble("age").intValue(),
                document.getString("gender"),
                document.getString("name")
        );
    }
}
