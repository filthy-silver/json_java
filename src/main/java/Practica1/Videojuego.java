package Practica1;

import java.util.ArrayList;

public class Videojuego {
    private String nombre;
    private String plataforma;
    private float precio;
    private boolean disponible;
    private ArrayList<String> generos;

    public Videojuego(String nombre, String plataforma, float precio, ArrayList<String> generos) {

        this.nombre = nombre;
        this.plataforma = plataforma;
        this.precio = precio;
        this.generos = generos;
        this.disponible = true;
    }

    public float getPrecio(){
        return precio;
    }

    public String toString(){
        return nombre + "  \t" + plataforma + "  \t" + precio + "€ \nGeneros:" + generos;
    }
}
