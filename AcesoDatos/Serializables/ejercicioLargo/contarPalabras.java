
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class contarPalabras extends JFrame implements ActionListener {

    private Container contenedor;
    private JLabel contarLabel;
    private JTextArea areaTexto;
    private JButton contarButton;
    private JButton seleccionarButton;
    private JTextArea rutaArchivo;
    private JLabel TotalPalabras;
    private JTextArea cantidadPalabras;
    private JScrollPane scrollPane;

    public contarPalabras() {
        inicio();
        setTitle("Contador de Vocales");
        setSize(580, 420); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        //Label
        contarLabel = new JLabel("Archivo:");
        contarLabel.setBounds(20, 20, 100, 25);

        TotalPalabras = new JLabel("Total Palabras:");
        TotalPalabras.setBounds(190, 320, 100, 25);

        //TextArea 
        rutaArchivo = new JTextArea();
        rutaArchivo.setBounds(80, 20, 330, 25);
        rutaArchivo.setEditable(false);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBounds(20, 60, 520, 200);

        cantidadPalabras = new JTextArea();
        cantidadPalabras.setBounds(280, 320, 100, 25);
        cantidadPalabras.setEditable(false);

        //Buttons
        seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.setBounds(420, 20, 120, 25);
        seleccionarButton.addActionListener(this);

        contarButton = new JButton("Contar Palabras");
        contarButton.setBounds(215, 275, 150, 30);
        contarButton.addActionListener(this);

        // Adds
        contenedor.add(contarLabel);
        contenedor.add(rutaArchivo);
        contenedor.add(seleccionarButton);
        contenedor.add(scrollPane);
        contenedor.add(contarButton);
        contenedor.add(TotalPalabras);
        contenedor.add(cantidadPalabras);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == seleccionarButton) {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showOpenDialog(this);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                rutaArchivo.setText(archivoSeleccionado.getAbsolutePath());

                try (BufferedReader lector = new BufferedReader(new FileReader(archivoSeleccionado))) {
                    String linea;
                    StringBuilder contenido = new StringBuilder();
                    while ((linea = lector.readLine()) != null) {
                        contenido.append(linea).append("\n");
                    }
                    areaTexto.setText(contenido.toString());
                } catch (IOException ex) {
                    areaTexto.setText("Error al leer el archivo: " + ex.getMessage());
                }
            }
        } else if (e.getSource() == contarButton) {
            String texto = areaTexto.getText().trim();

            if (texto.isEmpty()) {
                cantidadPalabras.setText("0");
            } else {
                String[] palabras = texto.split("\\s+");
                cantidadPalabras.setText(String.valueOf(palabras.length));
            }
        }
    }

    public static void main(String[] args) {
        contarPalabras cp = new contarPalabras();
        cp.setVisible(true);
    }
}
