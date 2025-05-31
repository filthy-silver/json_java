package org.example;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString

public class PersonaJSON {
    private String nombre;
    private int edad;
    private List<String> lenguajes;

    public PersonaJSON() {}

    public PersonaJSON(String nombre, int edad, List<String> lenguajes) {
        this.nombre = nombre;
        this.edad = edad;
        this.lenguajes = new ArrayList<>(lenguajes);
    }

}
