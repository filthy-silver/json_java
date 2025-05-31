package Practica1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Inventario {


    static Scanner sc = new Scanner(System.in);
    static ArrayList<Videojuego> inventario = new ArrayList<>();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("INVENTARIO" +
                "\n" + "♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠" );

        for (int i = 0; i < 3; i++) {
            crearVideojuego();
            System.out.println("\n > Videojuego añadido al inventario.\n");
        }

        /*
        ArrayList<PersonaJSON> listaPersonas = new ArrayList<>(Arrays.asList(new PersonaJSON("Luis", 25, Arrays.asList("Java", "Python")), new PersonaJSON("Patricia", 40, Arrays.asList("Java", "MongoDB"))));
        String json = gson.toJson(listaPersonas);
        System.out.println("JSON: " + json);
        * */

        String json = "";

        try (FileWriter writer = new FileWriter("src/main/resources/inventario.json")) {
            json = gson.toJson(inventario);
            writer.write(json);
            System.out.println("JSON guardado en inventario.json");
        } catch (Exception e) {
            System.out.println("Algo ha ido mal.");
            e.printStackTrace();
        }
        System.out.println("\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nJSON Prettyfied\n" + json);

        inventario.clear();

        System.out.println("\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nInventario vaciado");

        try (FileReader reader = new FileReader("src/main/resources/inventario.json")) {
            Videojuego[] videojuegosArray = gson.fromJson(reader, Videojuego[].class);
            if (videojuegosArray != null) {
                inventario = new ArrayList<>(Arrays.asList(videojuegosArray));
                System.out.println("\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nInventario cargado desde inventario.json");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar inventario:");
            e.printStackTrace();
        }

        System.out.println("\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nInventario cargado desde inventario.json");

        crearVideojuego();
        System.out.println("\n > Videojuego añadido al inventario.");

        System.out.println("♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nVideojuegos con precio menor a 30€:");

        for (Videojuego videojuego : inventario) {
            if (videojuego.getPrecio() < 30) {
                System.out.println(videojuego);
            }
        }

        System.out.println("\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nInventario actualizado, guardando en inventario.json");

        try (FileWriter writer = new FileWriter("src/main/resources/inventario.json")) {
            String json2 = gson.toJson(inventario);
            writer.write(json2);
            System.out.println("Inventario actualizado y guardado.");
        } catch (Exception e) {
            System.out.println("Algo ha ido mal.");
            e.printStackTrace();
        }

    }

    public static void crearVideojuego() {
        System.out.println("Introduce el nombre del videojuego:");
        String nombre = sc.nextLine();
        System.out.println("Introduce la plataforma del videojuego:");
        String plataforma = sc.nextLine();
        System.out.println("Introduce el precio del videojuego:");
        float precio = Float.parseFloat(sc.nextLine());
        System.out.println("Introduce los géneros del videojuego (separados por comas):");
        String generosInput = sc.nextLine();
        String[] generosArray = generosInput.split(",");
        ArrayList<String> generos = new ArrayList<>(Arrays.asList(generosArray));

        Videojuego videojuego = new Videojuego(nombre, plataforma, precio, generos);
        inventario.add(videojuego);
    }
}


