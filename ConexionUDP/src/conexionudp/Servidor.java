package conexionudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        /* Para UDP no necesitamos una clase que sea servidora, no 
        hay un Server Socket UDP, estan los datagramas.
         */
        final int PUERTO = 5000;
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Iniciado el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            while (true) {
                // Voy a crear un paquete que va contener este buffer, con esta longitud
                // No puedo meter mas de esa longitud
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                socketUDP.receive(peticion);
                System.out.println("Recibo la informacion del cliente");
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);

                // Consigo el puerto del cliente, a traves del paquete
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();

                mensaje = "Hola mundo desde el servidor!";
                buffer = mensaje.getBytes();

                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

                System.out.println("Envio informacion al cliente");
                socketUDP.send(respuesta);
            }

        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
