package com.company.UI;

import com.company.SerialCommunication.Microcontroller;

/**
 * Created by Nicu Stejar on 27-Feb-16.
 */
public class PortWindowController {
    private PortWindow portWindow;
    private MainWindowController mainWindowController;
    private Microcontroller microcontroller;

    /**
     * Creates the Controller for the {@link PortWindow}(MVC)
     * It controls the View({@link PortWindow}) and also connects with the controllers of {@link MainWindow} and
     * with the MCU ({@link Microcontroller})
     */
    public PortWindowController() {
    }

    /**
     * Sets the View ({@link PortWindow}) of the current controller
     * @param portWindow View of current controller
     */
    public void setPortWindow(PortWindow portWindow) {
        this.portWindow = portWindow;
    }

    /**
     * Connects with the controller of the {@link MainWindow}
     * @param mainWindowController the controller of Main Window
     */
    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    /**
     * Connects with the MCU
     * @param microcontroller the MCU
     */
    public void setMicrocontroller(Microcontroller microcontroller) {
        this.microcontroller = microcontroller;
        this.mainWindowController.setMicrocontroller(this.microcontroller);
    }

    /**
     * Reurns the MCU of current connection
     */
    public Microcontroller getMicrocontroller() {
        return microcontroller;
    }
}
