package server;

import client.GUI;
import utilities.Color;

public class main {
    int[][] gameBoard = new int[201][201]; //All values are default 0

    public main()
    {
        setupServer();
        runServer();
    }



    public void runServer()
    {

    }

    public void setupServer()
    {

    }

    public static void main(String[] args) { //entrypoint

        new GUI();
        new main();



    }
    public void onReceiveData(String message)
    {

        String[] args = message.split(":");
    }
}


