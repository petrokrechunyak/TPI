package com.studing;

import java.io.*;

public class Utils {

    public static String readFromFile(String path){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String str;
            StringBuilder builder = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                builder.append(str).append("\n");
            }
            return builder.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Файлу не існує, файл створено!");
            FileWriter writer = null;
            try {
                writer = new FileWriter(path);
                writer.close();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException exn) {
            System.out.println("Файл пустий!");
        }
        return null;
    }
}
