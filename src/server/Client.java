package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.SocketException;

public class Client {
    int id;
    Inet6Address address;
    int port;

    public Client(Inet6Address address, int port)
    {
        this.address = address;
        this.port = port;
    }
}

