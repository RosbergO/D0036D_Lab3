package server;

import java.net.Inet6Address;
import java.util.List;

public class ClientUtilities
{
    //Returns the index of the client in the list. If the client doesn't exist return -1
    public static int ClientIsInList(Inet6Address address, List<Client> clients)
    {
        for (Client client : clients)
        {
            if(address.toString().equals(client.address.toString()))
                return clients.indexOf(client);
        }
        return -1;
    }
}
