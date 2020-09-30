package client;

import java.util.Arrays;

public class NetworkParser {

    public int[] parse(String input) throws RuntimeException {
        String[] temp = input.split(":");
        int[] parsedArray = new int[3];
        int[] args = new int[temp.length];
        for (int i = 0; i < temp.length; i++)
        {
            System.out.println("Starting to parse " + temp[i].toString());
            args[i] = tryParse(temp[i], -1);
            if(args[i] == -1)
            {
                System.out.println("Couldn't parse " + temp[i] + ", returning");
                throw new RuntimeException("Couldnt parse");
            }
            parsedArray[i] = Integer.parseInt(temp[i]);
            System.out.println("successfully parsed " + args[i]);
        }
        return parsedArray;
    }

    public static int tryParse(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

}
