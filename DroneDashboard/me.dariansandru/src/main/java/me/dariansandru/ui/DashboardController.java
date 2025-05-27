package me.dariansandru.ui;

import me.dariansandru.domain.DroneData;
import me.dariansandru.network.ServerConnector;

import javax.swing.*;
import java.io.IOException;

public class DashboardController {
    private DroneDashboard view;
    private final ServerConnector serverConnector;

    public DashboardController(DroneDashboard view, String host, int port) throws IOException {
        this.view = view;
        final int timeoutMS = 10000;

        serverConnector = new ServerConnector(host, port, timeoutMS);
        serverConnector.connect();

        view.addSendDataListener(e -> {
            JOptionPane.showMessageDialog(view.frame, "Data sent to authorities!");
        });

        Timer timer = new Timer(3000, e -> {
            try {
                DroneData droneData = serverConnector.getCSVData();
                System.out.println(serverConnector.getCSVData().toString());
                view.updateDroneData(droneData);
            } catch (RuntimeException ex) {
                view.resetDashboard();
                System.err.println("Error receiving data: " + ex.getMessage());
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void disconnect() {
        if (serverConnector != null) {
            serverConnector.disconnect();
        }
    }
}