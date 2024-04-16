package conexionudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {

        /*
        Diferencia entre TCP Y UDP
        TCP: En TCP el servidor necesita saber quien esta conectado a el, para poder lanzar mensajes
        UDP: En el UDP no es necesario saber quien esta conectado a quien.
        
        CONCEPTOS EXTRA:
        Socket: Un socket es un extremo de comunicación, es decir, un objeto a traves del
        cual una aplicación de Windows Sockets envía o recibe los paquetes de datos a través de una red.
        
        Bufer: Un bufer es un espacio de memoria, en el que se almacenan datos de manera temporal, normalmente
        para un unico uso.
         */
        final int PUERTO_SERVIDOR = 5000;
        byte[] buffer = new byte[1024];

        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            DatagramSocket socketUDP = new DatagramSocket();

            String mensaje = "Hola mundo desde el cliente!";

            buffer = mensaje.getBytes();

            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);

            System.out.println("Envio el datagrama");
            socketUDP.send(pregunta);

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            socketUDP.receive(peticion);
            System.out.println("Recibo la peticion");
            mensaje = new String(peticion.getData());
            System.out.println(mensaje);

            socketUDP.close();

        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
