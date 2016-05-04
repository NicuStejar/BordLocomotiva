package com.company.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nicu Stejar on 25-Feb-16.
 */
public class MainWindow extends JFrame implements Runnable{
    private MainWindowController mainWindowController;

    private JButton connectButton;
    private JButton startButton;
    private JPanel mainPanel;
    private JButton disconnectButton;

    public MainWindow() {
        super("Engine Driver");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setContentPane(this.mainPanel);

        this.disconnectButton.setEnabled(false);
        this.startButton.setEnabled(false);

        super.pack();
        super.setVisible(true);
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    @Override
    public void run() {
        this.connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.this.mainWindowController.startPortWindow();
                MainWindow.this.connectButton.setEnabled(false);
                MainWindow.this.disconnectButton.setEnabled(true);
                MainWindow.this.startButton.setEnabled(true);
            }
        });

        this.disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.this.connectButton.setEnabled(true);
                MainWindow.this.disconnectButton.setEnabled(false);
            }
        });

        this.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.this.mainWindowController.startControlling();
                MainWindow.this.startButton.setEnabled(false);
            }
        });
    }
}
