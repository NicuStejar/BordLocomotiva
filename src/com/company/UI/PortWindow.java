package com.company.UI;

import com.company.SerialCommunication.Microcontroller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nicu Stejar on 25-Feb-16.
 */
public class PortWindow extends JFrame implements Runnable{
    private PortWindowController portWindowController;

    private String comPort;
    private int baudRate, dataBits, stopBits, parity;

    private JComboBox baudRateCombo;
    private JComboBox dataBitsCombo;
    private JComboBox stopBitsCombo;
    private JComboBox parityCombo;
    private JButton connectButton;
    private JPanel mainPanel;
    private JTextField comPortTextFiled;
    private JButton closeWindowButton;

    public PortWindow() {
        super("Connection settings");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setContentPane(this.mainPanel);
        super.pack();
        super.setVisible(true);
    }

    public void setPortWindowController(PortWindowController portWindowController) {
        this.portWindowController = portWindowController;
    }

    @Override
    public void run() {
        this.connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PortWindow.this.comPortTextFiled.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter COM port name (COMx)");
                }
                else {
                    PortWindow.this.comPort = PortWindow.this.comPortTextFiled.getText();
                    PortWindow.this.baudRate = Integer.parseInt(PortWindow.this.baudRateCombo.getSelectedItem().toString());
                    PortWindow.this.dataBits = Integer.parseInt(PortWindow.this.dataBitsCombo.getSelectedItem().toString());
                    PortWindow.this.stopBits = Integer.parseInt(PortWindow.this.stopBitsCombo.getSelectedItem().toString());
                    if (PortWindow.this.parityCombo.getSelectedIndex() == 0) {
                        PortWindow.this.parity = 1;
                    } else {
                        PortWindow.this.parity = 0;
                    }
                    PortWindow.this.portWindowController.setMicrocontroller(new Microcontroller(
                            PortWindow.this.comPort,
                            PortWindow.this.baudRate,
                            PortWindow.this.dataBits,
                            PortWindow.this.stopBits,
                            PortWindow.this.parity
                    ));
                    if (PortWindow.this.portWindowController.getMicrocontroller().isConnected()) {
                        JOptionPane.showMessageDialog(PortWindow.this, "Connected!");
                        PortWindow.this.connectButton.setEnabled(false);
                    }
                }
            }
        });

        this.closeWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PortWindow.this.dispose();
            }
        });
    }
}
