package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class SocketRecieve extends Thread
{
    NetworkParser parser = new NetworkParser();
    int[] parsedMessage;
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] buffer = new byte[20]; //10 bytes, maximum length of x:y:color:
    InetAddress address = Inet6Address.getByName("::1");//Inet6Address.getLocalHost();
    int port = 4445;
    Grids grid;
    Color[] color;

    public SocketRecieve(DatagramSocket socket, Grids grid, Color[] color) throws IOException, InterruptedException {
        this.socket = socket;
        this.grid = grid;
        this.color = color;
    }
    @Override
    public void run() {
        while(true)
        {
            packet = new DatagramPacket(buffer, buffer.length, address, port);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ReceiveSocket did thing");
            String message = new String(packet.getData(), StandardCharsets.UTF_8);
            parsedMessage = parser.parse(message);
            grid.setCell(parsedMessage[0], parsedMessage[1], color[parsedMessage[2]]);
            System.out.println(message);
            System.out.println(message.length());
        }
    }
}
