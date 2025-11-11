
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHilos {

    public static void main(String[] args) {

        String host = "localhost"; // o IP del servidor
        int puerto = 1234;         // mismo puerto del servidor

        //try con recursos
        try (
                Socket socket = new Socket(host, puerto); PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));) {
            String mensaje;
            System.out.println("Conectado al servidor. Escribe mensajes para enviar ( quit para salir ):");

            while (true) {
                System.out.print("Mensaje: ");
                mensaje = teclado.readLine();

                salida.println(mensaje);  // enviamos mensaje al servidor

                if (mensaje.trim().equals("quit")) {
                    System.out.println("Fin de transmisión.");
                    break;
                }

                // Leemos respuesta del servidor y mostramos
                String respuesta = entrada.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
