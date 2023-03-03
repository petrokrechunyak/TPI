package com.studing.ex2;

import com.studing.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Arrays;

public class Controller {

    private final String[] ukrainianAlphabet = new String[] { " ",
            "а", "б", "в", "г", "ґ", "д", "е", "є", "ж", "з", "и", "і", "ї",
            "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х",
            "ц", "ч", "ш", "щ", "ь", "ю", "я"
    };

    private final String KEY = "petro";

    private static String FILE = "file.txt";

    @FXML
    private Label encryptedField;

    @FXML
    private Label decryptedField;

    @FXML
    private Label fileText;

    @FXML
    void decrypt(ActionEvent event) {
        String text = Utils.readFromFile(FILE);
        encryptedField.setText("");
        decryptedField.setText(change(text, -STEP));
        fileText.setText(text);
    }

    @FXML
    void encrypt(ActionEvent event) {
        String text = Utils.readFromFile(FILE);
        decryptedField.setText("");
        encryptedField.setText(change(text, STEP));
        fileText.setText(text);
    }

    private String change(String str, int step) {
        StringBuilder builder = new StringBuilder();
        for(String c: str.split("")) {
            if(contains(ukrainianAlphabet, c)) {
                builder.append(findNewChar(ukrainianAlphabet, step, c));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    private String findNewChar(String[] arr, int step, String c) {
        String lowerCase = c.toLowerCase();
        int index = Arrays.asList(arr).indexOf(lowerCase);
        int newIndex = (index + step) % arr.length;
        if(newIndex < 0) {
            newIndex = arr.length + newIndex;
        }
        String newChar = arr[newIndex];
        return c.toLowerCase().equals(c) ? newChar : newChar.toUpperCase();
    }

    private String[] keyToArr(String key) {
        String[] arr = new String[key.length()];
        for(int i = 0; i < key.length(); i++) {
                        
        }
    }

    private boolean contains(String[] arr, String c) {
        return Arrays.stream(arr).anyMatch(c::equalsIgnoreCase);
    }


}
