package presentation.controller;

import javax.swing.*;
import java.awt.*;

public class AlertHelper {
    public static void displayError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
