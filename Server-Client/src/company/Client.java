/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kevse
 */
package company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;


public class Client {

    static Socket clientSocket;
    static String serverIp = "localhost";//"localhost";//127.0.0.1
    static int serverPort = 5000;
    static ObjectOutputStream clientOutput;
    static ObjectInputStream clientInput;
    
    public static void Connect(int port){
        try {
            // server ve socket baglantilari
            Client.clientSocket = new Socket(Client.serverIp, port);
            Client.clientInput = new ObjectInputStream(Client.clientSocket.getInputStream());
            Client.clientOutput = new ObjectOutputStream(Client.clientSocket.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {

        try {
            clientSocket = new Socket(serverIp, serverPort);
            clientInput = new ObjectInputStream(clientSocket.getInputStream());
            clientOutput = new ObjectOutputStream(clientSocket.getOutputStream());

            clientOutput.writeObject("merhaba server");
            clientOutput.writeObject("nasılsın server");
          
            String value = clientInput.readObject().toString();//blocking
            System.out.println("Server said:" + value);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
