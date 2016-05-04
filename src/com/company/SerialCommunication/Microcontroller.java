package com.company.SerialCommunication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import javax.swing.*;
import java.util.Enumeration;


public class Microcontroller implements SerialPortEventListener {
	private SerialPort serialPort;
	private String portName;
	private int baudRate, databits, stopbits, parity;
    private boolean isConnected;

	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;

    /**
     * Constructs the class for microcontroller objects
     * @param portName name of the COM Port used for connection (COMx)
     * @param baudRate speed of connection
     * @param databits number of bits for data
     * @param stopbits number of bits for stop
     * @param parity number of bits for parity(odd, even or none)
     */
	public Microcontroller(String portName, int baudRate, int databits, int stopbits, int parity) {
		this.portName = portName;
		this.baudRate = baudRate;
		this.databits = databits;
		this.stopbits = stopbits;
		this.parity = parity;
		this.isConnected = false;
	}

    /**
     * Method used for initializing the connection between computer and microcontroller.
     * It tries to connect to the MCU based on the params send to constructor
     */
	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		// First, try to connect to the portName
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
				if (currPortId.getName().equals(this.portName)) {
					portId = currPortId;
				}
		}
		if (portId == null) {
            JOptionPane.showMessageDialog(null, "Could not find " +  this.portName);
			return;
		}

        // Next, try to set the params (databits, stopbits and parity)
        try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(this.baudRate, this.databits, this.stopbits, this.parity);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);

			this.isConnected = true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Can't configure the parameters for " + this.portName);
		}
	}

    /**
     * Method used for writing data to serial connection
     * @param data is the string to be send
     */
	public void write(String data) {
		int dataWrite = Integer.parseInt(data);
		try {
			System.out.println("Sending data: '" + dataWrite + "'");
			this.output.write(dataWrite);
			this.output.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = input.readLine();
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

    /**
     Checks if the connection was established between MCU and Computer
     */
	public boolean isConnected() {
		return this.isConnected;
	}
}

