package com.company.UI;

import com.company.Data.EngineDataController;

/**
 * Created by Nicu Stejar on 25-Feb-16.
 */
public class DisplayWindowController {
    private DisplayWindow displayWindow;
    private MainWindowController mainWindowController;
    private EngineDataController engineDataController;

    /**
     * Creates the Controller for the {@link DisplayWindow}(MVC)
     * It controls the View({@link DisplayWindow}) and also connects with the controllers of {@link MainWindow} and
     * {@link com.company.Data.EngineData}
     */
    public DisplayWindowController() {
        ;
    }

    /**
     * Sets the {@link DisplayWindow} for the current controller (MVC)
     * @param displayWindow the View of this controller*/
    public void setDisplayWindow(DisplayWindow displayWindow) {
        this.displayWindow = displayWindow;
    }

    /**
     * Sets the {@link MainWindowController} for the {@link MainWindow} of the app
     * @param mainWindowController the controller for the window
     */
    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    /**Sets the {@link EngineDataController} for the {@link com.company.Data.EngineData}
     * @param engineDataController the controller of the data
     */
    public void setEngineDataController(EngineDataController engineDataController) {
        this.engineDataController = engineDataController;
    }

    /**
     * Returns the {@link EngineDataController} of the current data
     */
    public EngineDataController getEngineDataController() {
        return engineDataController;
    }
}
