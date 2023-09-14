package edu.eci.co.calculator;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class calculatorServer {
    public static void start() throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            List<String> line = new ArrayList<>();
            while ((inputLine = in.readLine()) != null) {
                //System.out.println("Recibí: " + inputLine);
                line.add(inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            String path = line.get(0).split(" ")[1];
            URI path1 = new URI(path);
            service s = calculator.Search(path1.getPath());
            System.out.println(path1.getPath());
            String res;
            if (s != null) {
                System.out.println(path.split("=")[1]);
                res = s.ejecutar(path.split("=")[1]);
            } else {
                res = "";
            }
            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: application/json\r\n"
                    + "\r\n"
                    + res;
            out.println(outputLine);
            out.close();

            in.close();

            clientSocket.close();
        }
        serverSocket.close();
    }
}
