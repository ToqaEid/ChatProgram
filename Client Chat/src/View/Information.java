/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author toqae
 */
public class Information {

    public boolean validateField(TextField field, Label error, String msg) {
        String str = field.getText().trim();
        if (str.length() == 0) {
            field.setText("");
            error.setText(msg);
            return false;
        }

        error.setText("");

        return true;
    }

    public boolean validateEmail(TextField email, Label error) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(email.getText());
        if (matcher.find() && matcher.group().equals(email.getText())) {
            error.setText("");
            return true;

        } else {
            email.setText("");
            error.setText("Please enter valid email");
            return false;
        }

    }

    void showPopUpMsg(String msg) {
    }

    void showNotification(String msg) {
    }
}
