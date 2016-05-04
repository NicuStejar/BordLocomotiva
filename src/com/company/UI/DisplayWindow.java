package com.company.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nicu Stejar on 25-Feb-16.
 */
public class DisplayWindow extends JFrame implements Runnable{
    private DisplayWindowController displayWindowController;

    private JLabel vitezaLabel;
    private JLabel presiuneCGLabel;
    private JLabel presiuneRezervorLabel;
    private JPanel mainPanel;
    private JButton closeButton;

    public DisplayWindow() {
        super("Locomotiva");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setContentPane(this.mainPanel);
        super.pack();
        super.setVisible(true);
    }

    public void setDisplayWindowController(DisplayWindowController displayWindowController) {
        this.displayWindowController = displayWindowController;
    }

    @Override
    public void run() {
        this.closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayWindow.this.setVisible(false);
            }
        });

        this.vitezaLabel.setText(Float.toString(
                this.displayWindowController.getEngineDataController().getEngineData().getSpeed()));
        this.presiuneCGLabel.setText(Float.toString(
                this.displayWindowController.getEngineDataController().getEngineData().getBrakePipePressureBAR()));
        this.presiuneRezervorLabel.setText(Float.toString(
                this.displayWindowController.getEngineDataController().getEngineData().getMainReservoirPressureBAR()));
    }
}
