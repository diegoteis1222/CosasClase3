
import java.awt.Container;
import java.awt.event.ActionEvent; // Import para poder cambiar la fuente
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

public class contarVocales extends JFrame implements ActionListener {

    private Container contenedor;
    private JLabel contarLabel;
    private JTextArea areaTexto;
    private JButton contarButton;
    private JButton seleccionarButton;
    private JTextArea rutaArchivo;
    private JLabel aLabel, eLabel, iLabel, oLabel, uLabel; // Etiquetas para "A:", "E:", etc.
    private JTextArea aCount, eCount, iCount, oCount, uCount; // Areas de texto para los números
    private JScrollPane scrollPane;

    public contarVocales() {
        inicio();
        setTitle("Contador de Vocales");
        setSize(580, 420); // Ajustamos el tamaño de la ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        //Label
        contarLabel = new JLabel("Archivo:");
        contarLabel.setBounds(20, 20, 100, 25);

        //TextArea 
        rutaArchivo = new JTextArea();
        rutaArchivo.setBounds(80, 20, 330, 25);
        rutaArchivo.setEditable(false);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBounds(20, 60, 520, 200);

        //Buttons
        seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.setBounds(420, 20, 120, 25);
        seleccionarButton.addActionListener(this);

        contarButton = new JButton("Contar Vocales");
        contarButton.setBounds(215, 275, 150, 30);
        contarButton.addActionListener(this);

        // Vocales
        aLabel = new JLabel("A:");
        aLabel.setBounds(100, 320, 20, 25);
        aCount = new JTextArea("0");
        aCount.setBounds(120, 320, 50, 25);
        aCount.setEditable(false);

        eLabel = new JLabel("E:");
        eLabel.setBounds(180, 320, 20, 25);
        eCount = new JTextArea("0");
        eCount.setBounds(200, 320, 50, 25);
        eCount.setEditable(false);

        iLabel = new JLabel("I:");
        iLabel.setBounds(260, 320, 20, 25);
        iCount = new JTextArea("0");
        iCount.setBounds(280, 320, 50, 25);
        iCount.setEditable(false);

        oLabel = new JLabel("O:");
        oLabel.setBounds(340, 320, 20, 25);
        oCount = new JTextArea("0");
        oCount.setBounds(360, 320, 50, 25);
        oCount.setEditable(false);

        uLabel = new JLabel("U:");
        uLabel.setBounds(420, 320, 20, 25);
        uCount = new JTextArea("0");
        uCount.setBounds(440, 320, 50, 25);
        uCount.setEditable(false);
        // joder, putas vocales

        // Adds
        contenedor.add(contarLabel);
        contenedor.add(rutaArchivo);
        contenedor.add(seleccionarButton);
        contenedor.add(scrollPane);
        contenedor.add(contarButton);
        contenedor.add(aLabel);
        contenedor.add(aCount);
        contenedor.add(eLabel);
        contenedor.add(eCount);
        contenedor.add(iLabel);
        contenedor.add(iCount);
        contenedor.add(oLabel);
        contenedor.add(oCount);
        contenedor.add(uLabel);
        contenedor.add(uCount);
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
            String texto = areaTexto.getText();
            int contadorA = 0;
            int contadorE = 0;
            int contadorI = 0;
            int contadorO = 0;
            int contadorU = 0;

            texto = texto.toLowerCase();

            for (int i = 0; i < texto.length(); i++) {
                char caracter = texto.charAt(i);
                switch (caracter) {
                    case 'a':
                    case 'á':
                        contadorA++;
                        break;
                    case 'e':
                    case 'é':
                        contadorE++;
                        break;
                    case 'i':
                    case 'í':
                        contadorI++;
                        break;
                    case 'o':
                    case 'ó':
                        contadorO++;
                        break;
                    case 'u':
                    case 'ú':
                    case 'ü':
                        contadorU++;
                        break;
                }
                //joder para las vocales de mierda
            }

            aCount.setText(String.valueOf(contadorA));
            eCount.setText(String.valueOf(contadorE));
            iCount.setText(String.valueOf(contadorI));
            oCount.setText(String.valueOf(contadorO));
            uCount.setText(String.valueOf(contadorU));
        }
    }

    public static void main(String[] args) {
        contarVocales cv = new contarVocales();
        cv.setVisible(true);
    }
}
