package server;

import utilities.Color;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {
    int[][] gameBoard = new int[20][20]; //All values are default 0
    List<Client> clients = new ArrayList<Client>();
    boolean running = false;
    private DatagramSocket socket;
    private int port;
    byte[] buf = new byte[65508];

    public Server() throws IOException {
        setupServer();
        runServer();
    }

    public void setupServer() throws SocketException {
        port = 4445;
        socket = new DatagramSocket(port);
        gameBoard[15][3] = 2;
        gameBoard[4][16] = 4;
    }

    public void runServer() throws IOException {
        running = true;
        while(running)
        {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            if(received.equals("join"))
                OnClientJoin(packet);
            else if(received.equals("draw"))
                drawGameBoard();
            else if (received.equals("end"))
                OnClientLeave(packet);
            else if(received.startsWith("color"))
                OnClientColor(packet);
        }
    }

    public void drawGameBoard()
    {
        System.out.println("Drawing game board");
        for (int y = 0; y < gameBoard[1].length; y++) //rows
        {
            for (int x = 0; x < gameBoard.length; x++) //columns
            {
                System.out.print(gameBoard[x][y]);
            }
            System.out.print("\n");
        }
        System.out.println("Finished drawing game board");

    }

    public static int tryParse(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }


    public int OnClientJoin(DatagramPacket packet) throws IOException {
        System.out.println("OnClientJoin()");

        if(ClientUtilities.ClientIsInList((Inet6Address) packet.getAddress(), clients) == -1)
        {
            System.out.println("Added client to list");

            clients.add(new Client((Inet6Address) packet.getAddress(), packet.getPort()));
        }

        int clientIndex = ClientUtilities.ClientIsInList((Inet6Address) packet.getAddress(), clients);
        clients.get(clientIndex).id = clientIndex;
        System.out.println("sendGameBoard(clientIndex)");
        SendGameBoard(clientIndex);
        return clientIndex;
    }

    private void SendGameBoard(int clientIndex) throws IOException {
        for (int y = 0; y < gameBoard[0].length; y++)
        {
            for (int x = 0; x < gameBoard.length; x++)
            {
                int color = gameBoard[x][y];
                if(color != 0)
                {
                    System.out.println("sendPacketToClient(" + clientIndex + ", " + x + ", " + y + ", " + color + ")");
                    SendPacketToClient(clientIndex, x, y, color);
                }
            }
        }
    }

    private void OnClientLeave(DatagramPacket packet) {
        int clientIndex = ClientUtilities.ClientIsInList((Inet6Address) packet.getAddress(), clients);
        if(clientIndex != -1)
        {
            System.out.println("removed client " + clientIndex);
            clients.remove(clientIndex);
        }
        else
        {
            System.out.println("Tried to remove client not in list.");
        }
    }

    private void SendPacketToClient(int clientIndex, int x, int y, int color) throws IOException {
        if(clientIndex >= 0 && clientIndex < clients.size())
        {
            Client client = clients.get(clientIndex);
            String message = x + ":" + y + ":" + color + ":";
            DatagramPacket packet = new DatagramPacket(buf, buf.length, client.address, client.port);
            packet.setData(message.getBytes());
            socket.send(packet);
        }
    }

    //sends packets to all clients except clientIndex, set clientIndex to -1 if you want to send to all
    private void SendPacketToClients(int clientIndex, int x, int y, int color) throws IOException {
        for (int i = 0; i < clients.size(); i++)
        {
            if(i != clientIndex)
            {
                SendPacketToClient(i, x, y, color);
            }
        }
    }

    public void OnClientColor(DatagramPacket packet) throws IOException {
        String message = new String(packet.getData(), StandardCharsets.UTF_8);
        int clientIndex = ClientUtilities.ClientIsInList((Inet6Address) packet.getAddress(), clients);

        if(clientIndex == -1) //if the client is not in the list, add to list then
        {
            clientIndex = OnClientJoin(packet);
        }

        /* Parses payload into gameboard modification */
        System.out.println("Received payload " + message);
        String[] temp = message.split(":");
        int[] args = new int[temp.length - 1];
        for (int i = 1; i < temp.length - 1; i++)
        {
            args[i - 1] = tryParse(temp[i], -1);
            if(args[i - 1] == -1)
            {
                System.out.println("Couldn't parse " + temp[i] + ", returning");
                return;
            }
        }
        if (args[0] >= 0 && args[0] < gameBoard.length) //x input is withing gameboard horizontally
        {
            if(args[1] >= 0 && args[1] < gameBoard[0].length) //y input is within gameboard vertically
            {
                if(args[2] >= 0 && args[2] <= Color.getColorArray().length) //color input is within color array
                {
                    gameBoard[args[0]][args[1]] = args[2];
                    SendPacketToClients(clientIndex, args[0], args[1], args[2]);
                }
            }
        }
    }
}



