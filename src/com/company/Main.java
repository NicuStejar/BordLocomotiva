package com.company;

import com.company.UI.MainWindow;
import com.company.UI.MainWindowController;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Error loading System Look and Feel");
            e.printStackTrace();
        }

        MainWindowController mainWindowController = new MainWindowController();
        MainWindow mainWindow = new MainWindow();
        mainWindowController.setMainWindow(mainWindow);
        mainWindow.setMainWindowController(mainWindowController);

        mainWindowController.startMainWindow();
    }

}
