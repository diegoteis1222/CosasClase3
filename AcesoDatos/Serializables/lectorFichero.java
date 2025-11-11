
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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class lectorFichero extends JFrame implements ActionListener {

    private Container contenedor;
    private JLabel mostrarContenido, aritmetica, ponderada;
    private JButton medias, cargar;
    private JTextField ruta;
    private JTextArea areaFichero, textoAritmetica, textoPonderada;

    public lectorFichero() {
        inicio();
        setTitle("Lector de Ficheros");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        //Label
        mostrarContenido = new JLabel("Mostrar contenido del archivo:");
        mostrarContenido.setBounds(80, 10, 500, 23);

        aritmetica = new JLabel("Media Aritmetica:");
        aritmetica.setBounds(80, 300, 300, 23);

        ponderada = new JLabel("Media Ponderada:");
        ponderada.setBounds(400, 300, 300, 23);

        //Buttons
        medias = new JButton("Calcular medias");
        medias.setBounds(260, 260, 270, 23);
        medias.addActionListener(this);

        cargar = new JButton("Cargar");
        cargar.setBounds(470, 80, 100, 23);
        cargar.addActionListener(this);

        //Area
        ruta = new JTextField();
        ruta.setBounds(80, 40, 500, 23);
        ruta.setEditable(false);

        //Fields
        areaFichero = new JTextArea();
        areaFichero.setBounds(100, 80, 350, 150);
        areaFichero.setEditable(false);

        textoAritmetica = new JTextArea();
        textoAritmetica.setBounds(200, 300, 150, 23);
        textoAritmetica.setEditable(false);

        textoPonderada = new JTextArea();
        textoPonderada.setBounds(530, 300, 150, 23);
        textoPonderada.setEditable(false);

        //Adds
        contenedor.add(mostrarContenido);
        contenedor.add(aritmetica);
        contenedor.add(ponderada);
        contenedor.add(medias);
        contenedor.add(cargar);
        contenedor.add(ruta);
        contenedor.add(areaFichero);
        contenedor.add(textoAritmetica);
        contenedor.add(textoPonderada);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cargar) {
            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showOpenDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = fileChooser.getSelectedFile();
                ruta.setText(fichero.getAbsolutePath());

                try {
                    StringBuilder contenido = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new FileReader(fichero));
                    String linea;

                    while ((linea = reader.readLine()) != null) {
                        contenido.append(linea).append("\n");
                    }
                    reader.close();

                    areaFichero.setText(contenido.toString());

                } catch (IOException ex) {
                    areaFichero.setText("Error al leer el archivo: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == medias) {
            String contenido = areaFichero.getText();
            if (contenido.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay datos para calcular.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Variables para la media aritmética
            double sumaAritmetica = 0;
            int contadorAritmetico = 0;

            // Variables para la media ponderada
            double sumaPonderada = 0;
            double sumaPesos = 0;

            String[] lineas = contenido.split("\\n");

            for (String linea : lineas) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(",");
                try {
                    // Calculo Aritmetica
                    double valor = Double.parseDouble(partes[0].trim());
                    sumaAritmetica += valor;
                    contadorAritmetico++;

                    //  Calculo Ponderado 
                    if (partes.length == 2) {
                        double peso = Double.parseDouble(partes[1].trim());
                        sumaPonderada += (valor * peso);
                        sumaPesos += peso;
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("Error de formato en la línea: '" + linea + "'. Se ignorará.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.err.println("Línea incompleta: '" + linea + "'. Se ignorará.");
                }
            }

            // Media Aritmética
            if (contadorAritmetico > 0) {
                double media = sumaAritmetica / contadorAritmetico;
                textoAritmetica.setText(String.format("%.5f", media));
            } else {
                textoAritmetica.setText("N/A");
            }

            // Media Ponderada
            if (sumaPesos > 0) {
                double mediaPond = sumaPonderada / sumaPesos;
                textoPonderada.setText(String.format("%.5f", mediaPond));
            } else {
                textoPonderada.setText("N/A");
            }
        }
    }

}
