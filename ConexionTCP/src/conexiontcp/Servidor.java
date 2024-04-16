package conexiontcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {
        
        ServerSocket servidor = null; // Socket del servidor
        Socket sc = null; // Socket del cliente
        DataInputStream in; // Para leer mensaje del cliente
        DataOutputStream out; // Para enviar mensaje al cliente
        
        final int PUERTO = 5000;
        
        try {
            // Creamos el servidor que escucha en el puerto 5000
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");
            
            // El servidor va estar siempre atento a escuchar peticiones del cliente.
            while(true){ // Va estar siempre escuchando, siempre a la espera
                sc = servidor.accept(); // accept(): este metodo se queda a la espera
                
                System.out.println("Cliente conectado");
                // Inicializa los objetos de entrada y salida para comunicarse con el cliente.
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());
                
                // Espero un mensaje del cliente
                String mensaje = in.readUTF(); // readUTF(): este metodo se queda a la espera de un mensaje del cliente
                
                System.out.println(mensaje);
                
                // Envio un mensaje al cliente
                out.writeUTF("Hola mundo desde el servidor!"); 
                
                sc.close(); // Cerramos el cliente
                System.out.println("Cliente desconectado");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
