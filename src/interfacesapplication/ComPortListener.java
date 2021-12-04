package interfacesapplication;

import java.util.Arrays;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author VeselovVV
 */
public class ComPortListener implements SerialPortEventListener {
    SerialPort port;
    
    public ComPortListener(SerialPort port) {
        this.port = port;
    }
    
    public void serialEvent(SerialPortEvent event) {
        // If there is data, read:
        if(event.isRXCHAR() && event.getEventValue() > 0){
            try {
                byte[] buffer = port.readBytes(event.getEventValue());
                
                // Set data to text fields:
                UserInterface.textInBytes.setText(Arrays.toString(buffer));
                UserInterface.textForFile.setText(new String(buffer));
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }
}
