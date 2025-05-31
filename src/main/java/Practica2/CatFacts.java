package Practica2;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

public class CatFacts {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("""
       
                                      |\\__/,|   (`\\
                CatFACTS            _.|o o  |_   ) )
                -------------------(((---(((--------
                """);

        boolean check = true;
        do{
            catFacts();
            System.out.println("Pulsa 'S' para salir o cualquier otra tecla para otro \"Fact\".");
            if (sc.nextLine().toLowerCase().equals("s")) {
                System.out.println("Saliendo...");
                check = false;
            }
        } while (check);

        System.out.println("""
              
                      |\\      _,,,---,,_
                ZZZzz /,`.-'`'    -.  ;-;;,_
                     |,4-  ) )-,_. ,\\ (  `'-'
                    '---''(_/--'  `-'\\_)  adiooos~\s""");

    }

    public static void catFacts() {
        try {
            String apiUrl = "https://catfact.ninja/fact";

            java.net.URL url = new java.net.URL(apiUrl);
            java.net.HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                json.append(line);
            }
            in.close();

            Gson gson = new com.google.gson.Gson();
            Root root = gson.fromJson(json.toString(), Root.class);

            System.out.println("    ~· " + root.fact);
            String factTraducido = traducirTexto(root.fact, "en", "es");
            System.out.println("[esp]: " + factTraducido + "\n");

        } catch (Exception e) {
            System.out.println("Algo ha ido mal.");
            e.printStackTrace();
        }
    }

    public static String traducirTexto(String texto, String idiomaOrigen, String idiomaDestino) {
        try {
            String textoEncoded = URLEncoder.encode(texto, "UTF-8");
            String urlTraduccion = "https://api.mymemory.translated.net/get?q=" +
                    textoEncoded + "&langpair=" + idiomaOrigen + "|" + idiomaDestino;

            java.net.URL url = new java.net.URL(urlTraduccion);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                json.append(line);
            }
            in.close();

            Gson gson = new Gson();
            TranslationResponse response = gson.fromJson(json.toString(), TranslationResponse.class);

            return response.responseData.translatedText;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error en traducción";
        }
    }
}
