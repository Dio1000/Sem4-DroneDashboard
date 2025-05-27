package me.dariansandru;

import me.dariansandru.ui.DashboardController;
import me.dariansandru.ui.DroneDashboard;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTextField hostField = new JTextField("localhost", 15);
            JTextField portField = new JTextField("12345", 15);

            JPanel panel = new JPanel(new GridLayout(2, 2));
            panel.add(new JLabel("Host:"));
            panel.add(hostField);
            panel.add(new JLabel("Port:"));
            panel.add(portField);

            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Drone Connection Setup",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String host = hostField.getText();
                    int port = Integer.parseInt(portField.getText());

                    DroneDashboard dashboard = new DroneDashboard();
                    DashboardController controller = new DashboardController(dashboard, host, port);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid port number", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }
            else System.exit(0);
        });
    }
}