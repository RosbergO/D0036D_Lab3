package client;

import java.io.IOException;
import java.net.*;

public class Client implements Runnable{
    private final int port;

    public Client(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try(DatagramSocket socket = new DatagramSocket(50000)) {
            byte[] buffer = new byte[10];
            String message = "hello";
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), Inet6Address.getLocalHost(), port);
            socket.send(packet);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void notifyGUI() {
        try(DatagramSocket clientSocket = new DatagramSocket(48000)) {
            byte[] buffer = new byte[5000];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
                clientSocket.receive(packet);
                String message = new String(packet.getData());
                System.out.println(message);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifyServer(String message) {
        try(DatagramSocket socket = new DatagramSocket(50000)) {
            //byte[] buffer = new byte[10];
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), Inet6Address.getLocalHost(), port);
            socket.send(packet);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
