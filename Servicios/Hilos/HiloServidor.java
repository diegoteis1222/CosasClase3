
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {

    BufferedReader fEntrada;
    PrintWriter fSalida;
    Socket socket = null;

    public HiloServidor(Socket s) {
        socket = s;

        try {
            fSalida = new PrintWriter(socket.getOutputStream(), true);
            fEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (Exception e) {
            System.out.println("Error al crear flujos: " + e.getMessage());
        }
    }

    public void run() {
        try {
            String cadena = "";

            while (!cadena.trim().equals("quit")) {
                System.out.println("Comunico con: " + socket.toString());
                cadena = fEntrada.readLine();

                if (cadena != null) {
                    break;
                }
                fSalida.println(cadena.trim().toUpperCase());

            }
            System.out.println("Fin con: " + socket.toString());
            fSalida.close();
            fEntrada.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error en la comunicación: " + e.getMessage());
        }
    }

}
