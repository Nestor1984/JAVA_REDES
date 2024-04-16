package conexiontcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {
        /*
        Diferencias entre TCP Y UDP:
        TCP es orientado a conexion
        UDP no es orientado a conexion
        
        Resumen:
        TCP: el servidor del TCP va saber quien se conecta, con que puerto, etc.
        UDP: el servidor del UDP no va saber quien se conecta.
         */
        final String HOST = "127.0.0.1"; // Dirección IP del servidor (localhost).
        final int PUERTO = 5000; // Puerto al que el cliente se conectará (mismo puerto que el servidor).
        DataInputStream in; // Objeto para leer datos desde el servidor.
        DataOutputStream out; // Objeto para enviar datos al servidor.

        try {
            // Crea un objeto Socket para conectarse al servidor.
            Socket sc = new Socket(HOST, PUERTO);

            // Inicializa los objetos de entrada y salida para comunicarse con el servidor.
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            
            // Escribimos un mensaje al servidor (RECORDAMOS QUE EL SERVIDOR ESTA ESPERANDO UN MENSAJE)
            out.writeUTF("Hola mundo desde el cliente!");
            
            // Recibo el mensaje del servidor
            String mensaje = in.readUTF();
            
            System.out.println(mensaje);
            
            // Cierra la conexión con el servidor.
            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
