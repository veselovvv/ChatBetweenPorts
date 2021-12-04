package interfacesapplication;

import static interfacesapplication.UserInterface.textForFileTCP;
import java.io.*;
import java.net.*;

/**
 *
 * @author VeselovVV
 */
public class Client {
    private static final int SERVER_PORT = 6666;
    private static final String LOCALHOST = "127.0.0.1";
    
    public static void main() {
        InetAddress ipAddress = null;
        DataOutputStream dOut = null;
        
        try {
            ipAddress = InetAddress.getByName(LOCALHOST);
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        }
        
        try (Socket socket = new Socket(ipAddress, SERVER_PORT)) {
            // Read text into byte array:
            byte[] bytes = textForFileTCP.getText().getBytes();
            
            // If there is no stream, create a stream:
            if (dOut == null) {
                dOut = new DataOutputStream(socket.getOutputStream());
            }
            
            dOut.writeInt(bytes.length); // write message size
            dOut.write(bytes); // write the message
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } 
    }
}
