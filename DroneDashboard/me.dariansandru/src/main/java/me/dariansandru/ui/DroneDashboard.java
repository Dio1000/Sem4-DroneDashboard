package me.dariansandru.ui;

import me.dariansandru.domain.DroneData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DroneDashboard extends JPanel {
    JFrame frame;
    private JPanel mainPanel;
    private JLabel statusLabel;
    private JLabel droneNameLabel;
    private JLabel coordinatesLabel;
    private JLabel fireDetectionLabel;
    private JLabel batteryLabel;
    private JLabel altitudeLabel;
    private JButton sendDataButton;
    private boolean hasData = false;

    public DroneDashboard() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Drone Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(600, 400));

        mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(50, 50, 50));
        JLabel titleLabel = new JLabel("DRONE DASHBOARD", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        centerPanel.setBackground(new Color(240, 240, 240));

        statusLabel = new JLabel("Waiting for Drone Data...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(statusLabel);

        droneNameLabel = new JLabel("", SwingConstants.CENTER);
        droneNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(droneNameLabel);

        coordinatesLabel = new JLabel("", SwingConstants.CENTER);
        coordinatesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(coordinatesLabel);

        fireDetectionLabel = new JLabel("", SwingConstants.CENTER);
        fireDetectionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(fireDetectionLabel);

        batteryLabel = new JLabel("", SwingConstants.CENTER);
        batteryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(batteryLabel);

        altitudeLabel = new JLabel("", SwingConstants.CENTER);
        altitudeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(altitudeLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        sendDataButton = new JButton("Send data to authorities");

        sendDataButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendDataButton.setBackground(new Color(220, 80, 60, 0));
        sendDataButton.setForeground(Color.WHITE);
        sendDataButton.setOpaque(true);
        sendDataButton.setContentAreaFilled(true);
        sendDataButton.setBorderPainted(false);
        sendDataButton.setEnabled(false);

        buttonPanel.add(sendDataButton);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public void updateDroneData(DroneData data) {
        hasData = true;
        statusLabel.setText("DRONE DATA RECEIVED");
        droneNameLabel.setText("Drone: " + data.getName());
        coordinatesLabel.setText("Coordinates: " + data.getLatitude() + ", " + data.getLongitude());

        if (data.isFireDetected()) {
            fireDetectionLabel.setText("Fire detected: YES (" + String.format("%.2f", data.getFireProbability()) + " probability)");
            fireDetectionLabel.setForeground(Color.RED);
        } else {
            fireDetectionLabel.setText("Fire detected: NO");
            fireDetectionLabel.setForeground(Color.GREEN);
        }

        batteryLabel.setText("Battery: " + data.getBatteryLevel() + "%");
        altitudeLabel.setText("Altitude: " + data.getAltitude() + " meters");
        sendDataButton.setEnabled(true);
    }

    public void resetDashboard() {
        hasData = false;
        statusLabel.setText("Waiting for Drone Data");
        droneNameLabel.setText("");
        coordinatesLabel.setText("");
        fireDetectionLabel.setText("");
        batteryLabel.setText("");
        altitudeLabel.setText("");
        sendDataButton.setEnabled(false);
    }

    public void addSendDataListener(ActionListener listener) {
        sendDataButton.addActionListener(listener);
    }
}
