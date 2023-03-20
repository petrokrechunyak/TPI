package com.studing.ex1;

import com.studing.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class Controller {

    private static final String[] englishAlphabet = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };

    private static final String[] ukrainianAlphabet = new String[] {
            "а", "б", "в", "г", "ґ", "д", "е", "є", "ж", "з", "и", "і", "ї",
            "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х",
            "ц", "ч", "ш", "щ", "ь", "ю", "я"
    };

    private final int STEP = 2;

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
        decryptedField.setText(change(encryptedField.getText(), -STEP));
    }

    @FXML
    void encrypt_keyboard(ActionEvent event) {
        String text = Utils.readFromFile(FILE);
        encryptedField.setText(change(decryptedField.getText(), STEP));
    }

    @FXML
    void decrypt_file(ActionEvent event) {
        String text = Utils.readFromFile(FILE);
        decryptedLabel.setText(change(text, -STEP));
        encryptedLabel.setText(text);
        fileText.setText("Текст у файлі: " + text);
    }

    @FXML
    void encrypt_file(ActionEvent event) {
        String text = Utils.readFromFile(FILE);
        decryptedLabel.setText(text);
        encryptedLabel.setText(change(text, STEP));
        fileText.setText("Текст у файлі: " + text);
    }

    private static String change(String str, int step) {
        StringBuilder builder = new StringBuilder();
        for(String c: str.split("")) {
            if(contains(englishAlphabet, c)) {
                builder.append(findNewChar(englishAlphabet, step, c));
            } else if(contains(ukrainianAlphabet, c)) {
                builder.append(findNewChar(ukrainianAlphabet, step, c));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    private static String findNewChar(String[] arr, int step, String c) {
        String lowerCase = c.toLowerCase();
        int index = Arrays.asList(arr).indexOf(lowerCase);
        int newIndex = (index + step) % arr.length;
        if(newIndex < 0) {
            newIndex = arr.length + newIndex;
        }
        String newChar = arr[newIndex];
        return c.toLowerCase().equals(c) ? newChar : newChar.toUpperCase();
    }

    private static boolean contains(String[] arr, String c) {
        return Arrays.stream(arr).anyMatch(c::equalsIgnoreCase);
    }
}
