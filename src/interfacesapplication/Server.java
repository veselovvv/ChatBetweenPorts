package interfacesapplication;

import static interfacesapplication.UserInterface.textForFileTCP;
import static interfacesapplication.UserInterface.textInBytesTCP;
import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 *
 * @author VeselovVV
 */
public class Server {
    private static final int PORT = 6666;
    
    public static void main() {
        InetAddress ia = null;
        
        try {
            ia = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        }
        
        try (ServerSocket serverSocket = new ServerSocket(PORT, 0, ia)) {
            while (true) {
                // Waiting for connection:
                Socket socket = serverSocket.accept();
                
                try (DataInputStream dIn = new DataInputStream(socket.getInputStream())) {
                    byte[] bytes = null;
                    int length = dIn.readInt(); // read message size
                    
                    if (length > 0) {
                        // Read the message:
                        bytes = new byte[length];
                        dIn.readFully(bytes, 0, bytes.length);
                        
                        // Set data to text fields:
                        textInBytesTCP.setText(Arrays.toString(bytes));
                        textForFileTCP.setText(new String(bytes));
                    }
                } 
            } 
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } 
    }
}
