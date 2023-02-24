package ru.ilka.app.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Person implements Serializable {
    //Имена полей в ковычках можно менять.
    //Изменения передаются в json, вывод будет соответствующий.
    @JsonProperty("Name")
    private final String firstName;

    @JsonProperty("Surname")
    private final String lastName;

    @JsonProperty("Age")
    private final int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
