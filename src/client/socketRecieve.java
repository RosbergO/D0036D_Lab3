package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class socketRecieve extends Thread
{
    NetworkParser parser = new NetworkParser();
    int[] parsedMessage;
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] buffer = new byte[20]; //10 bytes, maximum length of x:y:color:
    InetAddress address = Inet6Address.getByName("::1");//Inet6Address.getLocalHost();
    int port = 4445;
    Grids grid;

    public socketRecieve(DatagramSocket socket, Grids grid) throws IOException, InterruptedException {
        this.socket = socket;
        this.grid = grid;
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
            System.out.println("Received message: " + message);
            parsedMessage = parser.parse(message);
            if(parsedMessage == null)
                continue;
            grid.setCell(parsedMessage[0], parsedMessage[1], parsedMessage[2]);
            System.out.println(message);
            System.out.println(message.length());
        }
    }
}
