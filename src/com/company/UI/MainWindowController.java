package com.company.UI;

import com.company.Data.EngineDataController;
import com.company.SerialCommunication.Microcontroller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Nicu Stejar on 27-Feb-16.
 */
public class MainWindowController {
    private MainWindow mainWindow;
    private PortWindowController portWindowController;
    private PortWindow portWindow;
    private DisplayWindowController displayWindowController;
    private DisplayWindow displayWindow;
    private EngineDataController engineDataController;
    private Microcontroller microcontroller;

    /**
     * Constructor for the controller of {@link MainWindow} It also connects with the View and Controller of
     * Port Window, Main Window and with the {@link EngineDataController} and {@link Microcontroller}
     */
    public MainWindowController() {
    }

    /**
     * Sets the {@link MainWindow} of the current controller (MVC)
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Retuns the {@link EngineDataController} of current app
     */
    public EngineDataController getEngineDataController() {
        return engineDataController;
    }

    /**
     * Connects with the {@link Microcontroller}
     * @param microcontroller MCU
     */
    public void setMicrocontroller(Microcontroller microcontroller) {
        this.microcontroller = microcontroller;
    }

    /**
     * Starts the {@link DisplayWindow}
     * Every T seconds it reads from the file (using {@link EngineDataController} and parses data to
     * {@link Microcontroller} and {@link DisplayWindow}
     */
    public void startControlling() {
        this.displayWindow = new DisplayWindow();
        this.displayWindowController = new DisplayWindowController();

        this.displayWindowController.setMainWindowController(this);
        this.displayWindowController.setDisplayWindow(this.displayWindow);
        this.displayWindow.setDisplayWindowController(this.displayWindowController);

        this.engineDataController = new EngineDataController();
        this.displayWindowController.setEngineDataController(this.engineDataController);
        this.microcontroller.initialize();

        Timer timer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainWindowController.this.engineDataController.run();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                MainWindowController.this.displayWindow.run();
                MainWindowController.this.microcontroller.write(
                        Integer.toString(MainWindowController.this.engineDataController.getEngineData().getSpeed()));
            }
        });
        timer.start();
    }

    /**
     * Starts {@link PortWindow} and begins connection to {@link Microcontroller}
     */
    public void startPortWindow() {
        this.portWindow = new PortWindow();
        this.portWindowController = new PortWindowController();

        this.portWindowController.setMainWindowController(this);
        this.portWindowController.setPortWindow(this.portWindow);
        this.portWindow.setPortWindowController(this.portWindowController);

        this.portWindow.run();
    }

    /**
     * Starts {@link MainWindow}*/
    public void startMainWindow() {
        this.mainWindow.run();
    }
}