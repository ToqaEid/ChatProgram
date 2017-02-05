/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
            error.setText(msg);
            return false;
        }

        error.setText("");

        return true;
    }

    void showPopUpMsg(String msg) {
    }

    void showNotification(String msg) {
    }
}
