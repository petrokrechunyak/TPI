package com.studing.ex2;

import com.studing.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class Controller {

    private final String[] ukrainianAlphabet = new String[] {
            "а", "б", "в", "г", "ґ", "д", "е", "є", "ж", "з", "и", "і", "ї",
            "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х",
            "ц", "ч", "ш", "щ", "ь", "ю", "я"
    };

    private final String KEY = "петра";
    private static String FILE = "file.txt";

    @FXML
    private TextField encryptedField;

    @FXML
    private TextField decryptedField;

    @FXML
    private Label fileText;

    @FXML
    private Label decryptedLabel;

    @FXML
    private Label encryptedLabel;

    @FXML
    void decrypt_keyboard(ActionEvent event) {
        String text = Utils.readFromFile(FILE);
        decryptedField.setText(change(encryptedField.getText(), false));
    }

    @FXML
    void encrypt_keyboard(ActionEvent event) {
        String text = Utils.readFromFile(FILE);
        encryptedField.setText(change(decryptedField.getText(), true));
    }

    @FXML
    void decrypt_file(ActionEvent event) {
        String text = Utils.readFromFile(FILE);
        decryptedLabel.setText(change(text, false));
        encryptedLabel.setText(text);
        fileText.setText("Текст у файлі: " + text);
    }

    @FXML
    void encrypt_file(ActionEvent event) {
        String text = Utils.readFromFile(FILE);
        decryptedLabel.setText(text);
        encryptedLabel.setText(change(text, true));
        fileText.setText("Текст у файлі: " + text);
    }

    private String change(String str, boolean plus) {
        StringBuilder builder = new StringBuilder();
        int[] arr = keyToArr(KEY);
        int i = 0;
        for(String c: str.split("")) {
            if(contains(ukrainianAlphabet, c)) {
                builder.append(findNewChar(ukrainianAlphabet, plus ? arr[i] : -arr[i], c));
            } else {
                builder.append(c);
            }
            i = i == arr.length-1 ? 0 : i+1;
        }
        return builder.toString();
    }

    private String findNewChar(String[] arr, int step, String c) {
        String lowerCase = c.toLowerCase();
        int index = Arrays.asList(arr).indexOf(lowerCase);
        int newIndex = (index + step) % arr.length;
        if(newIndex < 0) {
            newIndex = Math.abs(newIndex);
        }
        String newChar = arr[newIndex];
        return c.toLowerCase().equals(c) ? newChar : newChar.toUpperCase();
    }

    private int[] keyToArr(String key) {
        int[] arr = new int[key.length()];
        for(int i = 0; i < key.length(); i++) {
            arr[i] = Arrays.asList(ukrainianAlphabet).indexOf(String.valueOf(key.charAt(i)));
        }
        return arr;
    }

    private boolean contains(String[] arr, String c) {
        return Arrays.stream(arr).anyMatch(c::equalsIgnoreCase);
    }


}
