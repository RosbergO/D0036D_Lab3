package client;

import java.io.IOException;
import java.net.*;

public class Client{
    private final int port;
    DatagramSocket socket;
    byte[] buffer = new byte[20]; //10 bytes, maximum length of x:y:color:
    InetAddress address;
    DatagramPacket packet;

    public Client(int port, DatagramSocket socket) throws UnknownHostException {
        this.port = port;
        this.socket = socket;
        packet = new DatagramPacket(buffer, buffer.length, address, port);
        packet.setData(buffer);
        address = Inet6Address.getByName("::1");
    }

    public void notifyServer(String message) throws IOException {
            packet = new DatagramPacket(buffer, buffer.length, address, port);
            packet.setData(message.getBytes());
            socket.send(packet);
    }
}
