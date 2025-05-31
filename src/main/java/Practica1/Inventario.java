package Practica1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Inventario {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Videojuego> inventarioVideojuegos = new ArrayList<>();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {

        System.out.println("INVENTARIO" +
                "\n" + "♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠");

        for (int i = 0; i < 3; i++) {
            crearVideojuego();
            System.out.println("\n > Videojuego añadido al inventario.\n");
        }

        try (FileWriter writer = new FileWriter("inventario.json")) {
            gson.toJson(inventarioVideojuegos, writer);
            System.out.println("JSON guardado en inventario.json");
        } catch (Exception e) {
            System.out.println("Algo ha ido mal.");
            e.printStackTrace();
        }
        System.out.println("\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nJSON Prettyfied" + inventarioVideojuegos);

        inventarioVideojuegos.clear();

        System.out.println("\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nInventario vaciado");

        try (FileWriter writer = new FileWriter("persona.json")) {
            gson.toJson(inventarioVideojuegos, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nInventario cargado desde inventario.json");

        crearVideojuego();
        System.out.println("\n > Videojuego añadido al inventario.\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nVideojuegos con precio menor a 30€:");

        for (Videojuego videojuego : inventarioVideojuegos) {
            if (videojuego.getPrecio() < 30) {
                System.out.println(videojuego);
            }
        }

        File file = new File("inventario.json");
        if (file.delete()) {
            System.out.println("Borrando inventario antiguo...");
        } else {
            System.out.println("Failed to delete persona.json.");
        }
        System.out.println("\n♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠\nInventario actualizado, guardando en inventario.json");

        try (FileReader reader = new FileReader("inventario.json")) {
            java.lang.reflect.Type type = new TypeToken<ArrayList<Videojuego>>(){}.getType();
            inventarioVideojuegos = gson.fromJson(reader, type);
            System.out.println("Inventario actualizado.");
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
        inventarioVideojuegos.add(videojuego);
    }
}