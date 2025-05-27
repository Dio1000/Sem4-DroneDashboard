package me.dariansandru.network;

import me.dariansandru.domain.DroneData;

import java.io.*;
import java.net.Socket;

public class ServerConnector {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private final String host;
    private final int port;
    private final int timeoutMs;

    public ServerConnector(String host, int port, int timeoutMs) {
        this.host = host;
        this.port = port;
        this.timeoutMs = timeoutMs;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        socket.setSoTimeout(timeoutMs);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public DroneData getCSVData() throws RuntimeException {
        try {
            String message = in.readLine();
            if (message == null) throw new RuntimeException("Drone did not send any message!");

            String[] parts = message.split(",");
            if (parts.length != 7)
                throw new RuntimeException("Data is corrupted! " +
                        "Data format: name, latitude, longitude, fireDetected, fireProbability, batteryLevel, altitude");

            return getDroneData(parts);

        } catch (IOException e) {
            throw new RuntimeException("Invalid message from Drone - Reason: " + e);
        }
    }

    private static DroneData getDroneData(String[] parts) {
        String name = parts[0];
        String latitude = parts[1];
        String longitude = parts[2];
        String fireDetected = parts[3];
        String fireProbability = parts[4];
        String batteryLevel = parts[5];
        String altitude = parts[6];

        DroneData droneData = new DroneData();
        droneData.setName(name);
        droneData.setLatitude(Double.parseDouble(latitude));
        droneData.setLongitude(Double.parseDouble(longitude));
        droneData.setFireDetected(fireDetected.equals("true"));
        droneData.setFireProbability(Double.parseDouble(fireProbability));
        droneData.setBatteryLevel(Double.parseDouble(batteryLevel));
        droneData.setAltitude(Double.parseDouble(altitude));

        return droneData;
    }

    public void disconnect() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.err.println("Error closing server connection: " + e.getMessage());
        }
    }
}